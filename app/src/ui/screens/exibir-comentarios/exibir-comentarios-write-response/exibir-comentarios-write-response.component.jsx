import './styles.css';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import {
  AuthorInfoHeader,
  InputTextArea,
  LayoutRow,
  LightButton,
  Loader,
} from '../../../components';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
} from '../../../../hooks';

export function ExibirComentarioWriteResponse({
  onPublishResponse,
  idComentario,
}) {
  const apiErrorMessage = useApiErrorMessageManager();
  const { exibirUsuarioBasico } = usePrivadoUsuarioApi();

  const { register, handleSubmit } = useForm();

  const [usuario, setUsuario] = useState(null);

  const _onPublishResponse = (body) => {
    onPublishResponse({ idComentario, ...body });
  };

  useEffect(() => {
    (async () => {
      const response = await exibirUsuarioBasico();

      if (response.error) apiErrorMessage.send(response.error);
      else setUsuario(response);
    })();
  }, []);

  if (!usuario) return <Loader cover />;

  return (
    <>
      <form
        onSubmit={handleSubmit(_onPublishResponse)}
        className="exibir-comentario-write-response"
      >
        <AuthorInfoHeader
          avatarSize={24}
          name={usuario.nome}
          photo={usuario.foto}
        >
          <InputTextArea
            register={register()}
            name="texto"
            placeholder="Escrever resposta"
            autoFocus
          />

          <LayoutRow alignEnd>
            <LightButton secondary submit mini>
              publicar resposta
            </LightButton>
          </LayoutRow>
        </AuthorInfoHeader>
      </form>
    </>
  );
}
