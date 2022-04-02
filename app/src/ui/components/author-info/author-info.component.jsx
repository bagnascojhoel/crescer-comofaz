import './styles.css';
import React from 'react';
import { Avatar } from '../';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../../routes';

export function AuthorInfo({
  avatarSize,
  name,
  photo,
  alignLeft,
  withoutBy,
  userId,
}) {
  return (
    <Link
      to={userId && `${ROUTES.PERFIL}/${userId}`}
      className={`
        author-info
        ${alignLeft && 'author-info--align-left'}
      `}
    >
      <Avatar diameter={avatarSize} url={photo} />
      <p className="author-info__name">
        {!withoutBy && 'Por'} {name}
      </p>
    </Link>
  );
}

AuthorInfo.defaultProps = {
  avatarSize: 42,
};
