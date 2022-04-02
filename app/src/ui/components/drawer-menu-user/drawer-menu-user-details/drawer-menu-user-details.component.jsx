import './styles.css';
import React from 'react';
import { Avatar, HorizontalDivider, BaseButton } from '../../';
import { ROUTES } from '../../../../routes';
import { VerticalSpacing } from '../../vertical-spacing/vertical-spacing.component';

export function DrawerMenuUserDetails({ onHide, nome, foto, idUsuario }) {
  if (!idUsuario) return <VerticalSpacing largeGutter />;

  return (
    <>
      <header className="drawer-menu-user-details">
        <BaseButton to={`${ROUTES.PERFIL}/${idUsuario}`} onClick={onHide}>
          <Content foto={foto} nome={nome} />
        </BaseButton>
      </header>
      <HorizontalDivider />
    </>
  );
}

function Content({ foto, nome }) {
  return (
    <>
      <Avatar url={foto} diameter={105} />
      <h2 className="drawer-menu-user-details__name">{nome}</h2>
    </>
  );
}
