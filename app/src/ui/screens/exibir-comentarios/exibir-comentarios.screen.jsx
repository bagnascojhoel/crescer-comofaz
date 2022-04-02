import './styles.css';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  useFileSend,
  usePrivadoComentarioApi,
  usePrivadoRespostaApi,
  usePublicoPostApi,
  usePublicoDefinicoesApi,
  useApiErrorMessageManager,
} from '../../../hooks';
import {
  BackgroundContent,
  BaseButton,
  Container,
  Loader,
  Title,
} from '../../components';
import chatIcon from '../../../assets/chat.svg';
import { ExibirComentariosWriteComment } from './exibir-comentarios-write-comment/exibir-comentarios-write-comment.component';
import { ExibirComentariosCommentsList } from './exibir-comentarios-comments-list/exibir-comentarios-comments-list.component';
import { useGlobalUser } from '../../../context';
import { ROUTES } from '../../../routes';

export function ExibirComentariosScreen() {
  const { tituloPost, idPost } = useParams();
  const apiErrorMessage = useApiErrorMessageManager();

  const { convertFormData } = useFileSend();
  const { buscarPostNaoLogado } = usePublicoPostApi();
  const { cadastrarComentario } = usePrivadoComentarioApi();
  const { cadastrarResposta } = usePrivadoRespostaApi();
  const { listarDefinicoes } = usePublicoDefinicoesApi();

  const [globalUser] = useGlobalUser();
  const [post, setPost] = useState(null);
  const [definicoesOptions, setDefinicoesOptions] = useState(null);

  const handlePublishComment = async (body) => {
    const formData = convertFormData(body, null, body.foto);
    const response = await cadastrarComentario({
      idPost,
      formData,
    });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      const newPost = { ...post };
      const { comentarios } = newPost;

      const newComentarios =
        comentarios !== null ? [response, ...comentarios] : [response];

      newPost.comentarios = newComentarios;

      setPost(newPost);
    }
  };

  const handlePublishResponse = async (body) => {
    const response = await cadastrarResposta({ ...body });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      const newPost = { ...post };
      const { comentarios } = post;
      const newComentarios = [...comentarios];

      const comentarioIndex = newComentarios.findIndex(
        ({ idComentario }) => idComentario === body.idComentario
      );

      const { respostas } = comentarios[comentarioIndex];

      const newRespostas = [...respostas, response];

      newComentarios[comentarioIndex].respostas = newRespostas;
      newPost.comentarios = newComentarios;

      setPost(newPost);
    }
  };

  useEffect(() => {
    (async () => {
      const response = await buscarPostNaoLogado(idPost);
      if (response.error) apiErrorMessage.send(response.error);
      else setPost(response);
    })();

    (async () => {
      const response = await listarDefinicoes();

      if (!response.error) setDefinicoesOptions(response.map(mapToOption));
      else apiErrorMessage.send(response.error);
    })();
  }, []);

  if (!post) return <Loader fullScreen large />;

  return (
    <>
      <Container>
        <Title>{tituloPost}</Title>
        <div className="exibir-comentarios-subtitle">
          <img src={chatIcon} alt="" />

          <h2 className="exibir-comentarios-subtitle__text">comentários</h2>
        </div>
      </Container>

      <BackgroundContent className="exibir-comentarios-comentar">
        {globalUser ? (
          <ExibirComentariosWriteComment
            definicoesOptions={definicoesOptions}
            onPublishComment={handlePublishComment}
          />
        ) : (
          <h2 className="exibir-comentarios-comentar__not-authorized">
            <BaseButton to={ROUTES.AUTENTICACAO}>Faça o login</BaseButton> para
            comentar aqui.
          </h2>
        )}
      </BackgroundContent>

      <ExibirComentariosCommentsList
        myName={post.usuarioNome}
        myPhoto={post.usuarioFoto}
        comentarios={post.comentarios}
        definicoesOptions={definicoesOptions}
        onPublishResponse={handlePublishResponse}
      />
    </>
  );
}

function mapToOption(definicao) {
  let label;
  switch (definicao) {
    case 'CONTRIBUICAO':
      label = 'Contribuição';
      break;
    case 'DUVIDA':
      label = 'Dúvida';
      break;
    case 'ELOGIO':
      label = 'Elogio';
      break;
    default:
      label = definicao;
  }

  return { label, value: definicao };
}
