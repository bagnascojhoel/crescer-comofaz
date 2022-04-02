import './styles.css';
import React, { useState } from 'react';
import {
  AuthorInfoHeader,
  Container,
  HorizontalDivider,
  LayoutRow,
  LightButton,
  OutlinedContent,
  VerticalSpacing,
} from '../../../components';
import { ExibirComentarioWriteResponse } from '../exibir-comentarios-write-response/exibir-comentarios-write-response.component';
import { ExibirComentariosResponseList } from '../exibir-comentarios-response-list/exibir-comentarios-response-list.component';
import { useGlobalUser } from '../../../../context';

export function ExibirComentariosLayoutComment({
  idComentario,
  usuarioIdUsuario,
  usuarioNome,
  usuarioFoto,
  myName,
  myPhoto,
  texto,
  foto,
  respostas,
  onPublishResponse,
}) {
  const [globalUser] = useGlobalUser();
  const [showWriteResponse, setShowWriteResponse] = useState(false);

  const handleShowWriteResponse = () => {
    setShowWriteResponse(true);
  };

  const _onPublishResponse = (body) => {
    setShowWriteResponse(false);
    onPublishResponse(body);
  };

  return (
    <Container>
      <AuthorInfoHeader
        avatarSize={42}
        name={usuarioNome}
        photo={usuarioFoto}
        userId={usuarioIdUsuario}
      >
        {foto && (
          <img
            className="exibir-comentarios-layout-comment__image"
            src={foto}
            alt=""
          />
        )}

        <OutlinedContent className="exibir-comentarios-layout-comment">
          <p className="exibir-comentarios-layout-comment__text">{texto}</p>
        </OutlinedContent>
      </AuthorInfoHeader>

      {globalUser && (
        <LayoutRow alignEnd>
          <LightButton
            onClick={handleShowWriteResponse}
            hidden={showWriteResponse}
            secondary
            mini
          >
            responder
          </LightButton>
        </LayoutRow>
      )}

      <HorizontalDivider
        hidden={respostas.length === 0 && !showWriteResponse}
        smallGutter
      />

      <ExibirComentariosResponseList respostas={respostas} />

      {showWriteResponse && (
        <ExibirComentarioWriteResponse
          onPublishResponse={_onPublishResponse}
          usuarioNome={myName}
          usuarioFoto={myPhoto}
          idComentario={idComentario}
        />
      )}
      <VerticalSpacing />
    </Container>
  );
}
