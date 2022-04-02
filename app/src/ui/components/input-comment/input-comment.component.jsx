import './styles.css';
import React from 'react';
import { AuthorInfo, InputTextArea } from '..';
import { SPACING } from '../../../styles';

const avatarSize = 42;

export function InputComment({
  register,
  authorName,
  authorPhoto,
  response,
  withoutBy,
}) {
  return (
    <div className="comment">
      <AuthorInfo
        withoutBy={withoutBy}
        avatarSize={avatarSize}
        name={authorName}
        photo={authorPhoto}
        alignLeft
      />

      <InputTextArea
        register={register}
        style={{ marginLeft: avatarSize + SPACING.GUTTER }}
        className="comment-content comment-input"
        placeholder={response ? 'Escrever resposta' : 'Escrever comentário'}
        noMargin
      />
    </div>
  );
}
