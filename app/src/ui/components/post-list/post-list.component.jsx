import './styles.css';
import React from 'react';
import { PostListItem } from './post-list-item/post-list-item.component';

export function PostList({onRemoverFavorito, dataList, keyExtractor, emptyMessage,  }) {
  if (dataList.length === 0)
    return (
      <div className="post-list--empty">
        {emptyMessage ? emptyMessage : 'Não há nenhum post ainda'}
      </div>
    );

  return (
    <ul className="post-list">
      {dataList.map((post) => (
        <PostListItem onRemoverFavorito={onRemoverFavorito} key={keyExtractor(post)} {...post} />
      ))}
    </ul>
  );
}
