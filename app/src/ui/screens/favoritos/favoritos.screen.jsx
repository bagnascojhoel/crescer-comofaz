import React, { useEffect, useState } from 'react';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
} from '../../../hooks';
import {
  Container,
  Loader,
  PostList,
  Subtitle,
} from '../../components';

export function FavoritosScreen() {
  const apiErrorMessage = useApiErrorMessageManager();

  const { exibirFavoritos } = usePrivadoUsuarioApi();
  const [posts, setPosts] = useState();

  const handleRemoverFavorito = (id) => {
    const newPosts = posts.filter(({ idPost }) => id !== idPost);
    setPosts(newPosts);
  };

  useEffect(() => {
    (async () => {
      const response = await exibirFavoritos();

      if (response.error) apiErrorMessage.send(response.error);
      else setPosts(response);
    })();
  }, []);

  if (!posts) return <Loader fullScreen large />;

  return (
    <Container>
      <Subtitle>Favoritos</Subtitle>
      <PostList
        dataList={posts}
        keyExtractor={(p) => p.idPost}
        onRemoverFavorito={handleRemoverFavorito}
      />
    </Container>
  );
}
