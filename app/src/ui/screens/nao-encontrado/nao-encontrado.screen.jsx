import React from 'react';
import { Redirect } from 'react-router-dom';
import { ROUTES } from '../../../routes';

export function NaoEncontradoScreen() {
  return (
    <>
      <Redirect to={ROUTES.HOME} />
    </>
  );
}
