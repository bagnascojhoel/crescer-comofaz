import './styles.css';
import React, { useEffect, useState } from 'react';
import { useForm, Controller } from 'react-hook-form';
import {
  AuthorInfoHeader,
  InputImage,
  InputSelect,
  InputTextArea,
  LayoutRow,
  LightButton,
  Loader,
} from '../../../components';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
} from '../../../../hooks';

export function ExibirComentariosWriteComment({
  definicoesOptions,
  onPublishComment,
}) {
  const apiErrorMessage = useApiErrorMessageManager();

  const { exibirUsuarioBasico } = usePrivadoUsuarioApi();
  const { register, handleSubmit, control, reset, watch } = useForm({
    defaultValues: {
      texto: null,
      foto: null,
      definicao: 'CONTRIBUICAO',
    },
  });

  const [usuario, setUsuario] = useState(null);

  const _onPublishComment = (body) => {
    onPublishComment(body);
    reset();
  };

  useEffect(() => {
    (async () => {
      const response = await exibirUsuarioBasico();

      if (response.error) apiErrorMessage.send(response.error);
      else setUsuario(response);
    })();
  }, []);

  if (!definicoesOptions || !usuario) return <Loader />;

  return (
    <>
      <form onSubmit={handleSubmit(_onPublishComment)}>
        <AuthorInfoHeader
          avatarSize={42}
          name={usuario.nome}
          photo={usuario.foto}
        >
          <InputTextArea
            register={register}
            name="texto"
            placeholder="Escrever comentário"
            noMargin
          />

          <LayoutRow className="exibir-comentarios-comment__layout-row">
            <InputImage control={control} name="foto" small/>

            <div className="exibir-comentarios-comment__aside-fields">
              <Controller
                as={InputSelect}
                control={control}
                name="definicao"
                options={definicoesOptions}
                className="exibir-comentarios-comment__select-definicao"
                noMargin
              />

              <LightButton hidden={!watch('texto')} secondary mini submit>
                publicar
              </LightButton>
            </div>
          </LayoutRow>
        </AuthorInfoHeader>
      </form>
    </>
  );
}
