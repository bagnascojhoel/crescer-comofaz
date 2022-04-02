import './styles.css';
import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import {
  usePublicoPostApi,
  usePrivadoPostApi,
  useApiErrorMessageManager,
} from '../../../hooks';
import {
  AuthorInfo,
  BackgroundContent,
  Container,
  LayoutLabel,
  LayoutRow,
  LightButton,
  MainButton,
  OutlinedContent,
  Rating,
  Subtitle,
  Title,
  Loader,
  PostCover,
  CategoriaList,
} from '../../components';
import { ROUTES } from '../../../routes';
import { useGlobalUser } from '../../../context';
import { ExibirPostPasso } from './exibir-post-passo/exibir-post-passo.component';
import { ExibirPostSugeridos } from './exibir-post-sugeridos/exibir-post-sugeridos.component';
import { ExibirPostAdicionarALista } from './exibir-post-adicionar-a-lista/exibir-post-adicionar-a-lista.component';
import { ExibirPostMarcarFeito } from './exibir-post-marcar-feito/exibir-post-marcar-feito.component';

export function ExibirPostScreen() {
  const { idPost } = useParams();
  const { push } = useHistory();

  const apiErrorMessage = useApiErrorMessageManager();
  const { buscarPostNaoLogado } = usePublicoPostApi();
  const { buscarPost } = usePrivadoPostApi();

  const [globalUser] = useGlobalUser();
  const [post, setPost] = useState();

  const handleExibirComentarios = () => {
    push(`${ROUTES.COMENTARIOS_DE_POST}/${idPost}`);
  };

  useEffect(() => {
    if (!post || idPost !== post.idPost) {
      (async () => {
        let postResponse = {};

        if (globalUser) {
          postResponse = await buscarPost(idPost);
        } else {
          postResponse = await buscarPostNaoLogado(idPost);
        }

        if (postResponse.error) {
          apiErrorMessage.send(postResponse.error);
          push(ROUTES.HOME);
        } else {
          setPost(postResponse);
        }
        window.scrollTo(0, 0);
      })();
    }
  }, [idPost]);

  if (!post) return <Loader fullScreen large />;

  return (
    <>
      <Container>
        {globalUser && globalUser.idUsuario === post.usuarioIdUsuario && (
          <LayoutRow alignEnd>
            <LightButton to={`${ROUTES.EDITAR_POST}/${idPost}`}>
              Editar post
            </LightButton>
          </LayoutRow>
        )}

        <Title>{post.titulo}</Title>

        <CategoriaList forLayout row categorias={post.tags} />

        <LayoutRow>
          <ExibirPostAdicionarALista idPost={idPost} />

          <ExibirPostMarcarFeito idPost={idPost} />
        </LayoutRow>
      </Container>

      <PostCover
        idPost={idPost}
        src={post.imagemCapa}
        favorito={post.favorito}
      />

      <Container>
        <LayoutRow className="exibir-post__layout-row">
          <AuthorInfo
            name={post.usuarioNome}
            photo={post.usuarioFoto}
            userId={globalUser.idUsuario}
          />
          <MainButton
            to={`${ROUTES.PERFIL}/${post.usuarioIdUsuario}`}
            small
            outlined
          >
            Visitar perfil
          </MainButton>
        </LayoutRow>

        <p className="exibir-post__descricao">{post.descricao}</p>

        <LayoutRow className="exibir-post__layout-row" alignStart>
          <LayoutLabel>Dificuldade</LayoutLabel>
          <Rating value={post.dificuldade} />
        </LayoutRow>

        <LayoutRow className="exibir-post__layout-row">
          <LayoutLabel>Tempo de execução</LayoutLabel>
          <OutlinedContent>{showTime(post.duracaoMinutos)}</OutlinedContent>
        </LayoutRow>
      </Container>

      <BackgroundContent className="exibir-post-materiais">
        <Subtitle>Você vai precisar de</Subtitle>
        {post.materiais.map((m, i) => (
          <p className="exibir-post-materiais__material" key={i}>
            {m}
          </p>
        ))}
      </BackgroundContent>

      <Container className="exibir-post-modo-de-fazer">
        <Subtitle>Modo de fazer</Subtitle>
        {post.passos.map(({ texto, foto }, i) => (
          <ExibirPostPasso photo={foto} text={texto} position={i + 1} key={i} />
        ))}
      </Container>

      {post.dica && (
        <BackgroundContent>
          <Subtitle className="exibir-post-dica__subtitle">Dica</Subtitle>
          <p className="exibir-post-dica__text">{post.dica}</p>
        </BackgroundContent>
      )}

      <Container>
        <MainButton
          className="exibir-post__ver-comentarios-button"
          onClick={handleExibirComentarios}
        >
          Ver comentários
        </MainButton>
      </Container>

      <ExibirPostSugeridos idPost={idPost} />
    </>
  );
}

function showTime(timeInMinutes) {
  let time, sufix;
  if (timeInMinutes < 60) {
    time = timeInMinutes;
    sufix = time === 1 ? 'minuto' : 'minutos';
  } else if (timeInMinutes < 1440) {
    time = timeInMinutes / 60;
    sufix = time === 1 ? 'hora' : 'horas';
  } else {
    time = timeInMinutes / 1440;
    sufix = time === 1 ? 'dia' : 'dias';
  }

  return `${time} ${sufix}`;
}
