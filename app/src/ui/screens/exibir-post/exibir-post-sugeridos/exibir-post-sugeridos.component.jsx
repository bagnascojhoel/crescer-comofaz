import React, { useEffect, useState } from 'react';
import { useGlobalUser } from '../../../../context';
import {
  useApiErrorMessageManager,
  usePrivadoPostApi,
  usePublicoPostApi,
} from '../../../../hooks';
import {
  BackgroundContent,
  Container,
  Loader,
  PostList,
  Subtitle,
} from '../../../components';

export function ExibirPostSugeridos({ idPost }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const {
    listarSugestoesPorPostNaoLogado,
    buscarListasPorPostNaoLogado,
  } = usePublicoPostApi();
  const { listarSugestoesPorPost } = usePrivadoPostApi();

  const [globalUser] = useGlobalUser();
  const [postsSugeridos, setPostsSugeridos] = useState(null);
  const [listasSugeridas, setListasSugeridas] = useState(null);

  useEffect(() => {
    (async () => {
      let postSugeridosResponse = {};
      let listasSugeridasResponse = {};

      if (globalUser) {
        postSugeridosResponse = await listarSugestoesPorPost({ idPost });
        listasSugeridasResponse = await buscarListasPorPostNaoLogado({
          idPost,
        });
      } else {
        postSugeridosResponse = await listarSugestoesPorPostNaoLogado({
          idPost,
        });
        listasSugeridasResponse = await buscarListasPorPostNaoLogado({
          idPost,
        });
      }

      if (listasSugeridasResponse.error)
        apiErrorMessage.send(listasSugeridasResponse.error);
      else setListasSugeridas(listasSugeridasResponse);

      if (postSugeridosResponse.error)
        apiErrorMessage.send(postSugeridosResponse.error);
      else setPostsSugeridos(postSugeridosResponse);
      console.log(postSugeridosResponse);
    })();
  }, []);

  if (!listasSugeridas || !postsSugeridos) return <Loader cover />;

  return (
    <BackgroundContent>
      <Container>
        <Subtitle>Posts sugeridos</Subtitle>

        <PostList dataList={postsSugeridos} keyExtractor={(p) => p.idPost} />
      </Container>
    </BackgroundContent>
  );
}
