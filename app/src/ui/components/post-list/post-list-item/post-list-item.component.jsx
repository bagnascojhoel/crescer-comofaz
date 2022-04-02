import './styles.css';
import React from 'react';
import { MainButton, Title, PostCover } from '../..';
import { ROUTES } from '../../../../routes';
import { PostListItemDifficulty } from '../post-list-item-difficulty/post-list-item-difficulty.component';

export function PostListItem({
  onRemoverFavorito,
  idPost,
  titulo,
  dificuldade,
  usuarioNome,
  imagemCapa,
  favorito,
}) {
  
  return (
    <div className="post">
      <div className="post__title-container">
        <Title className="post__title">{mobileTrimmerTitle(titulo)}</Title>
      </div>
      <PostListItemDifficulty value={dificuldade} />
      <PostCover
        idPost={idPost}
        src={imagemCapa}
        onRemoverFavorito={onRemoverFavorito}
        favorito={favorito}
        small
      />
      <h3 className="post__author">Por {usuarioNome}</h3>

      <MainButton
        className="post__access"
        to={`${ROUTES.EXIBIR_POST}/${idPost}`}
        small
      >
        Acessar
      </MainButton>
    </div>
  );
}

function mobileTrimmerTitle(title) {
  const size = 30;
  if (title.length > size) {
    return title.substr(0, size - 3) + '...';
  } else if (title.length === size) {
    return title.substr(0, size);
  } else {
    return title;
  }
}
