import './styles.css';
import React from 'react';
import { Rating } from '../../rating/rating.component';

export function PostListItemDifficulty({ value }) {
  return (
    <div className="post__difficulty">
      <span>Dificuldade</span>
      <Rating value={value} flexible />
    </div>
  );
}
