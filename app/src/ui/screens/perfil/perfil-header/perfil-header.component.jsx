import './styles.css';
import React from 'react';
import { Avatar } from '../../../components';
import lampIcon from '../../../../assets/lamp.svg';

export function PerfilHeader({ name, photo, contributions }) {
  return (
    <header className="perfil-header">
      <Avatar url={photo} diameter={128} />
      <h2 className="perfil-header__name">{name}</h2>
      <h3 className="perfil-header-contributions">
        <img
          className="perfil-header-contributions__icon"
          src={lampIcon}
          alt=""
        />
        {contributions} contribuições
      </h3>
    </header>
  );
}
