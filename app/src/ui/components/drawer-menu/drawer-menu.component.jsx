import './styles.css';
import React from 'react';
import { DrawerMenuCloseButton } from './drawer-menu-close-button/drawer-menu-close-button.component';
import { DrawerMenuBackground } from './drawer-menu-background/drawer-menu-background.component';

export function DrawerMenu({ onHide, show, children, right }) {
  return (
    <>
      <nav
        className={`
          drawer-menu
          ${show && 'drawer-menu--visible'}
          ${right && 'drawer-menu--right'}
      `}
      >
        <DrawerMenuCloseButton onHide={onHide} />
        {children}
      </nav>
      <DrawerMenuBackground onHide={onHide} show={show} />
    </>
  );
}

DrawerMenu.defaultProps = {
  show: false,
};
