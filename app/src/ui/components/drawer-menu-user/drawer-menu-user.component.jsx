import './styles.css';
import React, { useEffect, useState } from 'react';
import { MainButton } from '../';
import { DrawerMenuUserDetails } from './drawer-menu-user-details/drawer-menu-user-details.component';
import { DrawerMenuUserNav } from './drawer-menu-user-nav/drawer-menu-user-nav.component';
import { DrawerMenu } from '../drawer-menu/drawer-menu.component';
import { ROUTES } from '../../../routes';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
} from '../../../hooks';
import { useGlobalUser } from '../../../context';

export function DrawerMenuUser({ onHide, show }) {
  const apiErrorMessage = useApiErrorMessageManager();

  const [globalUser] = useGlobalUser();
  const { exibirUsuarioBasico } = usePrivadoUsuarioApi();
  const [perfilBasico, setPerfilBasico] = useState(null);

  useEffect(() => {
    if (globalUser) {
      (async () => {
        const response = await exibirUsuarioBasico();
  
        if (response.error) apiErrorMessage.send(response.error);
        else setPerfilBasico(response);
      })();
    }

  }, []);
  
  return (
    <DrawerMenu onHide={onHide} show={show}>
      <DrawerMenuUserDetails onHide={onHide} {...perfilBasico} />

      <DrawerMenuUserNav onHide={onHide} authenticated={!!perfilBasico} />

      {perfilBasico && (
        <MainButton
          className="drawer-menu-user__cadastrar-button"
          to={ROUTES.CADASTRAR_POST}
          onClick={onHide}
        >
          criar como faz
        </MainButton>
      )}
    </DrawerMenu>
  );
}

DrawerMenuUser.defaultProps = {
  show: false,
};