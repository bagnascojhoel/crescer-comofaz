import './styles.css';
import React, { useEffect, useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import {
  useApiErrorMessageManager,
  useFileSend,
  usePrivadoUsuarioApi,
} from '../../../hooks';
import { ROUTES } from '../../../routes';
import {
  Container,
  InputImage,
  InputText,
  LayoutRow,
  LightButton,
  Loader,
  MainButton,
  VerticalSpacing,
} from '../../components';
import { useHistory } from 'react-router-dom';

export function EditarPerfilScreen() {
  const { push } = useHistory();
  const apiErrorMessage = useApiErrorMessageManager();
  const { exibirMeuPerfil, editarPerfil } = usePrivadoUsuarioApi();
  const { convertFormData } = useFileSend();

  const [usuario, setUsuario] = useState();

  const { register, handleSubmit, control } = useForm({
    defaultValues: {
      foto: null,
    },
  });

  const handleEditarInteresses = () => {
    const selected = usuario.interesses
      .map(({ idCategoria }) => idCategoria)
      .join(',');
    push(`${ROUTES.EDITAR_INTERESSES}/${selected}`);
  };

  const handleEditar = async (body) => {
    const formData = convertFormData(body, null, body.foto);
    const response = await editarPerfil({ formData });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      const novoUsuario = { ...usuario };
      novoUsuario.nome = response.nome;
      novoUsuario.foto = response.foto;

      setUsuario(novoUsuario);
      window.location.reload();
    }
  };

  useEffect(() => {
    (async () => {
      const response = await exibirMeuPerfil();

      if (response.error) {
        apiErrorMessage.send(response.error);
      } else {
        setUsuario(response);
      }
    })();
  }, []);

  if (!usuario) return <Loader fullScreen large />;

  return (
    <>
      <Container>
        <LayoutRow alignEnd>
          <LightButton onClick={handleEditarInteresses}>
            editar interesses
          </LightButton>
        </LayoutRow>

        <form onSubmit={handleSubmit(handleEditar)}>
          <LayoutRow alignBottom>
            <div className="editar-perfil-foto">
              <img
                src={usuario.foto}
                alt="Sua foto"
                className="editar-perfil-foto__foto"
              />

              <InputImage
                control={control}
                name="foto"
                className="editar-perfil-foto__input"
              />
            </div>

            <InputText
              register={register}
              name="nome"
              label="Nome"
              defaultValue={usuario.nome}
              noMargin
              className="editar-perfil-nome"
            />
          </LayoutRow>

          <VerticalSpacing />

          <LayoutRow alignEnd>
            <MainButton submit small>
              confirmar novos dados
            </MainButton>
          </LayoutRow>
        </form>
      </Container>
    </>
  );
}
