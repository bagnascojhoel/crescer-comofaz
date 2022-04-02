import './styles.css';
import React, { useState } from 'react';
import { IconButton } from '../icon-button/icon-button.component';
import menuButtonIcon from '../../../assets/menu-button.svg';
import notificationIcon from '../../../assets/notification.svg';
import notificationExistsIcon from '../../../assets/notification-exists.svg';
import { DrawerMenuUser } from '../drawer-menu-user/drawer-menu-user.component';
import { DrawerMenuNotifications } from '..';
import { PageHeaderLogo } from '../page-header-logo/page-header-logo.component';
import { useGlobalUser } from '../../../context';
import { useAuthRequiredMessageManager } from '../../../hooks';

export const PAGE_HEADER_BUTTON_SIZE = 24;

export function PageHeader() {
  const authRequiredMessage = useAuthRequiredMessageManager();

  const [globalUser] = useGlobalUser();

  const [hasNotificacoes, setHasNotificacoes] = useState(false);
  const [showDrawerMenuUser, setShowDrawerMenuUser] = useState(false);
  const [showDrawerNotifications, setShowDrawerNotifications] = useState(false);

  const handleSetHasNotificacoes = (hasNotificacoes) => {
    setHasNotificacoes(hasNotificacoes);
  };

  const handleUpdateVisibilityMenu = () => {
    setShowDrawerMenuUser(!showDrawerMenuUser);
  };

  const handleUpdateVisibilityNotifications = () => {
    if (!globalUser) authRequiredMessage.handleShow();
    else setShowDrawerNotifications(!showDrawerNotifications);
  };

  return (
    <>
      <header className="page-header">
        <IconButton
          onClick={handleUpdateVisibilityMenu}
          iconUrl={menuButtonIcon}
          size={PAGE_HEADER_BUTTON_SIZE}
        />
        <PageHeaderLogo />
        <IconButton
          onClick={handleUpdateVisibilityNotifications}
          iconUrl={hasNotificacoes ? notificationExistsIcon : notificationIcon}
          size={PAGE_HEADER_BUTTON_SIZE}
        />
      </header>
      <DrawerMenuUser
        onHide={handleUpdateVisibilityMenu}
        show={showDrawerMenuUser}
      />
      <DrawerMenuNotifications
        onHide={handleUpdateVisibilityNotifications}
        onChange={handleSetHasNotificacoes}
        show={showDrawerNotifications}
      />
    </>
  );
}
