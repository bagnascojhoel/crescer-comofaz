import './styles.css';
import React, { useEffect, useState } from 'react';
import { HorizontalDivider } from '../horizontal-divider/horizontal-divider.component';
import { DrawerMenu } from '../drawer-menu/drawer-menu.component';
import {
  useApiErrorMessageManager,
  usePrivadoNotificacaoApi,
} from '../../../hooks';
import { DrawerMenuNotificationList } from './drawer-menu-notification-list/drawer-menu-notification-list.component';
import { useGlobalUser } from '../../../context';

export function DrawerMenuNotifications({ onHide, onChange, show }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const { buscarNovasNotificacoes } = usePrivadoNotificacaoApi();

  const [globalUser] = useGlobalUser();
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    if (globalUser) {
      const fetchNotificacoes = async () => {
        const response = await buscarNovasNotificacoes();

        if (response.error) apiErrorMessage.send(response.error);
        else setNotifications(response);
      };

      fetchNotificacoes();

      const interval = setInterval(fetchNotificacoes, 20000);

      return () => clearInterval(interval);
    }

    return;
  }, []);

  useEffect(() => {
    onChange(notifications.length > 0);
  }, [notifications]);

  return (
    <DrawerMenu onHide={onHide} show={show} right>
      <HorizontalDivider noMarginBottom />
      <DrawerMenuNotificationList data={notifications} onHide={onHide} />
    </DrawerMenu>
  );
}
