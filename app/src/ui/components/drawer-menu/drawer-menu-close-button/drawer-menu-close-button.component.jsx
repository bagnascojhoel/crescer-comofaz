import './styles.css'
import React from 'react';
import closeIcon from '../../../../assets/close.svg';
import { IconButton } from '../../';

export function DrawerMenuCloseButton({ onHide }) {
  return (
    <div className="drawer-menu__close-button">
      <IconButton onClick={onHide} size={30} iconUrl={closeIcon} />
    </div>
  );
}
