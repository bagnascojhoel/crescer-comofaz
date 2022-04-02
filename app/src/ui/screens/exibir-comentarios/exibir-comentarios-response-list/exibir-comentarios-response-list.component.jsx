import './styles.css';
import React from 'react';
import {
  AuthorInfoHeader,
  OutlinedContent,
  VerticalSpacing,
} from '../../../components';

export function ExibirComentariosResponseList({ respostas }) {
  return (
    <div className="exibir-comentarios-response-list">
      {respostas.map(
        ({ usuarioIdUsuario, usuarioNome, usuarioFoto, texto }, i) => (
          <div key={i}>
            <AuthorInfoHeader
              avatarSize={24}
              name={usuarioNome}
              photo={usuarioFoto}
              userId={usuarioIdUsuario}
            >
              <OutlinedContent className="exibir-comentarios-response-list__content">
                <p className="exibir-comentarios-response-list__texto">
                  {texto}
                </p>
              </OutlinedContent>
            </AuthorInfoHeader>
            <VerticalSpacing />
          </div>
        )
      )}
    </div>
  );
}
