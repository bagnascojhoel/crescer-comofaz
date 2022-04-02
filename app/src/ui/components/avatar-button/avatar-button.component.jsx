import React from 'react';
import { Avatar } from '../avatar/avatar.component';
import { BaseButton } from '../base-button/base-button.component';

export function AvatarButton({ onClick, to, url, diameter }) {
  return (
    <BaseButton onClick={onClick} to={to}>
      <Avatar url={url} diameter={diameter} />
    </BaseButton>
  );
}
