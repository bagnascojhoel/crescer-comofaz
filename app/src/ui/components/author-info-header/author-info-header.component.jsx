import React from 'react';
import { AuthorInfo } from '../';
import { SPACING } from '../../../styles';

export function AuthorInfoHeader({
  avatarSize,
  name,
  photo,
  userId,
  children,
}) {
  return (
    <div>
      <header>
        <AuthorInfo
          avatarSize={avatarSize}
          name={name}
          photo={photo}
          userId={userId}
          alignLeft
          withoutBy
        />
      </header>
      <div style={{ marginLeft: avatarSize + SPACING.GUTTER }}>{children}</div>
    </div>
  );
}
