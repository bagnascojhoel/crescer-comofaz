import React, { useEffect, useState } from 'react';
import { usePrivadoUsuarioApi } from '../../../hooks';
import {
  Container,
  Loader,
  PostList,
  Subtitle,
} from '../../components';

export function FeitosScreen() {
  const { exibirFeitos } = usePrivadoUsuarioApi();
  const [posts, setPosts] = useState();

  useEffect(() => {
    (async () => {
      const response = await exibirFeitos();
      setPosts(response);
    })();
  }, []);

  if (!posts) return <Loader fullScreen large />;

  return (
    <Container>
      <Subtitle>Feitos</Subtitle>
      <PostList dataList={posts} keyExtractor={(p) => p.idPost} />
    </Container>
  );
}
