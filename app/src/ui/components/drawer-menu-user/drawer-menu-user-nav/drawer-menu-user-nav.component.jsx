import './styles.css';
import React from 'react';
import { ROUTES } from '../../../../routes';
import { Link, useHistory } from 'react-router-dom';
import { useAuthManager, useToastMessageManager } from '../../../../hooks';

export function DrawerMenuUserNav({ authenticated, onHide }) {
  const { push } = useHistory();
  const { logout } = useAuthManager();
  const { send } = useToastMessageManager();

  const handleLogout = async () => {
    await logout();
    send('Deslogado com sucesso');
    push(ROUTES.AUTENTICACAO);
  };

  const props = {
    onHide,
    authenticated,
  };

  return (
    <ul className="drawer-menu-user-nav">
      <ActionListItem to={ROUTES.HOME} text="home" primary {...props} />

      {!authenticated && (
        <ActionListItem to={ROUTES.AUTENTICACAO} text="login" {...props} />
      )}

      <ActionListItem
        to={ROUTES.EDITAR_PERFIL}
        text="editar perfil"
        authenticate
        {...props}
      />

      <ActionListItem
        to={ROUTES.FEITOS}
        text="feitos"
        authenticate
        {...props}
      />

      <ActionListItem
        to={ROUTES.FAVORITOS}
        text="favoritos"
        authenticate
        {...props}
      />

      <ActionListItem
        to={ROUTES.SUAS_LISTAS}
        text="suas listas"
        authenticate
        {...props}
      />

      <ActionListItem
        to=""
        onClick={handleLogout}
        text="logout"
        authenticate
        {...props}
      />
    </ul>
  );
}

function ActionListItem({
  to,
  onClick,
  text,
  primary,
  onHide,
  authenticated,
  authenticate,
}) {
  if (authenticate && !authenticated) return <></>;

  const handleClick = () => {
    if (onClick) onClick();
    onHide();
  };

  return (
    <li
      className={`
        drawer-menu-user-nav__item
        ${primary && 'drawer-menu-user-nav__item--primary'}
      `}
    >
      <Link
        to={to}
        onClick={handleClick}
        className={`
          drawer-menu-user-nav__link
          ${primary && 'drawer-menu-user-nav__link--primary'}
        `}
      >
        {text}
      </Link>
    </li>
  );
}
