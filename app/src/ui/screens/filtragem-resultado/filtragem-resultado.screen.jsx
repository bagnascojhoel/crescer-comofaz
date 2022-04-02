import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useGlobalUser } from '../../../context';
import {
  useApiErrorMessageManager,
  usePrivadoPostApi,
  usePublicoPostApi,
} from '../../../hooks';
import {
  Container,
  Loader,
  PostList,
  Subtitle,
  VerticalSpacing,
} from '../../components';

export function FiltragemResultadosScreen(props) {
  const { selected } = useParams();
  const apiErrorMessage = useApiErrorMessageManager();
  const publicoPostApi = usePublicoPostApi();
  const privadoPostApi = usePrivadoPostApi();

  const [globalUser] = useGlobalUser();
  const [posts, setPosts] = useState(null);

  useEffect(() => {
    (async () => {
      let response;
      const idCategorias = selected.split(',');
      if (globalUser) {
        response = await privadoPostApi.filtrarCategoria({ idCategorias });
      } else {
        response = await publicoPostApi.filtrarCategoriaNaoLogado({
          idCategorias,
        });
      }

      if (response.error) apiErrorMessage.send(response.error);
      else setPosts(response);
    })();
  }, []);

  if (!posts) return <Loader fullScreen large />;

  return (
    <Container>
      <Subtitle>Resultado da filtragem</Subtitle>
      <PostList
        dataList={posts}
        keyExtractor={({ idPost }) => idPost}
        emptyMessage="Nenhum post nessas categorias"
      />
      <VerticalSpacing />
    </Container>
  );
}
