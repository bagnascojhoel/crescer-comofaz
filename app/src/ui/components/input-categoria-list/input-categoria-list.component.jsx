import React, { useEffect, useState } from 'react';
import {
  useApiErrorMessageManager,
  usePrivadoCategoriaApi,
  usePublicoCategoriaApi,
} from '../../../hooks';
import { useController } from 'react-hook-form';
import { CategoriaList } from '../categoria-list/categoria-list.component';
import { LayoutLabel } from '../layout-label/layout-label.component';
import { Loader } from '../loader/loader.component';
import { useGlobalUser } from '../../../context';

export function InputCategoriaList({ control, name, defaultValue, noLabel }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const { field } = useController({
    name,
    control,
    rules: { required: true },
    defaultValue: defaultValue || [],
  });

  const privadoCategoriaApi = usePrivadoCategoriaApi();
  const publicoCategoriaApi = usePublicoCategoriaApi();

  const [globalUser] = useGlobalUser();

  const [categorias, setCategorias] = useState(null);

  const _onChange = (idCategoria) => {
    if (!field.value.includes(idCategoria))
      field.onChange([...field.value, idCategoria]);
    else field.onChange(field.value.filter((id) => idCategoria !== id));
  };

  useEffect(() => {
    (async () => {
      let response;
      console.log(globalUser);
      if (globalUser) {
        response = await privadoCategoriaApi.buscarCategorias();
      } else {
        response = await publicoCategoriaApi.buscarCategorias();
      }

      if (response.error) apiErrorMessage.send(response.error);
      else setCategorias(response);
    })();
  }, []);

  if (!categorias) return <Loader cover />;

  return (
    <div className="input-categoria-list">
      {!noLabel && (
        <LayoutLabel htmlFor={name} className="input__label">
          Categorias
        </LayoutLabel>
      )}

      <CategoriaList
        className="cadastrar-post__categories-list"
        categorias={categorias}
        value={field.value}
        onChange={_onChange}
        label="Categorias"
      />
    </div>
  );
}
