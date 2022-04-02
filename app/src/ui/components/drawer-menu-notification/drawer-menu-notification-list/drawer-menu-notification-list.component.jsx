import React from 'react';
import { ROUTES } from '../../../../routes';
import { usePrivadoNotificacaoApi } from '../../../../hooks';
import { BaseButton } from '../../base-button/base-button.component';

export function DrawerMenuNotificationList({ onHide, data }) {
  const { atualizarNotificacao } = usePrivadoNotificacaoApi();

  const handleUpdateNotificacao = (id) => {
    atualizarNotificacao(id);
    onHide();
  };

  return (
    <ul className="drawer-menu-notifications__list">
      {data && data.length > 0
        ? data.map((notificacao, i) => (
            <Item
              key={i}
              id={notificacao.idNotificacao}
              onClick={handleUpdateNotificacao}
              to={`${ROUTES.COMENTARIOS_DE_POST}/${notificacao.postIdPost}`}
              tipo={notificacao.tipo}
              usuario={notificacao.usuarioDaInteracaoNome}
              titulo={notificacao.postTitulo}
            />
          ))
        : 'Você não possui novas notificações'}
    </ul>
  );
}

function Item({ onClick, id, to, tipo, usuario, titulo }) {
  const _onClick = () => {
    onClick(id);
  };

  let message;
  if (tipo === 'COMENTARIO') {
    message = `${usuario} deixou um comentário em "${titulo}"`;
  } else {
    message = `${usuario} respondeu seu comentário em "${titulo}"`;
  }

  return (
    <li className="drawer-menu-notifications__item">
      <BaseButton
        className="drawer-menu-notifications__item__button"
        onClick={_onClick}
        to={to}
      >
        {message}
      </BaseButton>
    </li>
  );
}
