import './styles.css';
import React from 'react';
import { Container, PostList, VerticalSpacing } from '../../../components';

export function PerfilCreations({ posts }) {
  return (
    <Container>
      <VerticalSpacing largeGutter/>
      <PostList
        dataList={posts}
        keyExtractor={({ idPost }) => idPost}
        emptyMessage="Este usuario não possui posts publicados"
      />
      <VerticalSpacing />
    </Container>
  );
}
