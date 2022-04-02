import './styles.css';
import React, { useEffect } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import {
  usePublicoCategoriaApi,
  usePrivadoCategoriaApi,
  useApiErrorMessageManager,
} from '../../../hooks';
import {
  Container,
  InputCategoriaList,
  Title,
  VerticalSpacing,
} from '../../components';
import { BaseButton } from '../../components/base-button/base-button.component';
import { ROUTES } from '../../../routes';
import { useGlobalUser } from '../../../context';

export function FiltragemScreen() {
  const { selected: params } = useParams();
  const history = useHistory();
  const { control, setValue, watch } = useForm();

  const handleClearSelection = () => {
    history.replace(`${ROUTES.FILTRAGEM}/`);
    setValue('categorias', []);
  };

  const handleFilter = () => {
    const query = watch('categorias').join(',');

    history.replace(`${ROUTES.FILTRAGEM}/${query}`);
    history.push(`${ROUTES.FILTRAGEM_RESULTADO}/${query}`);
  };

  useEffect(() => {
    if (params) {
      const previous = params.split(',').map((v) => parseInt(v));
      setValue('categorias', previous);
    }
  }, []);

  return (
    <Container>
      <Title>Filtrar categorias</Title>

      <VerticalSpacing />

      <InputCategoriaList control={control} name="categorias" noLabel />
      <div className="filtrar-categorias-actions">
        {watch('categorias') && watch('categorias').length > 0 && (
          <FiltragemButton onClick={handleClearSelection} outlined>
            limpar
          </FiltragemButton>
        )}
        <FiltragemButton onClick={handleFilter}>filtrar</FiltragemButton>
      </div>
    </Container>
  );
}

function FiltragemButton({ onClick, outlined, children }) {
  return (
    <BaseButton
      onClick={onClick}
      className={`
        filtrar-categorias-actions__button
        ${outlined && 'filtrar-categorias-actions__button--outlined'}
      `}
    >
      {children}
    </BaseButton>
  );
}
