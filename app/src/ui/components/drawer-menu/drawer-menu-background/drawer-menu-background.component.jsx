import './styles.css';
import React from 'react';

export function DrawerMenuBackground({ onHide, show }) {
  return (
    <div
      onClick={onHide}
      className={`
        drawer-menu__background
        ${show && 'drawer-menu__background--visible'}
      `}
    ></div>
  );
}
