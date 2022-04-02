DROP TABLE lista_post;
DROP TABLE lista;
DROP TABLE feito;
DROP TABLE favorito;
DROP TABLE resposta;
DROP TABLE comentario;
DROP TABLE passo;
DROP TABLE post_categoria;
DROP TABLE post_materiais;
DROP TABLE post;
DROP TABLE usuario_categoria;
DROP TABLE categoria;
DROP TABLE usuario;

CREATE TABLE usuario(
    id_usuario INT GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    foto VARCHAR2(512),
    id_provedor VARCHAR2(255) NOT NULL,
    tipo_provedor VARCHAR2(10) NOT NULL,
    CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario),
    CONSTRAINT UN_PROVIDER UNIQUE (id_provedor, tipo_provedor),
    CONSTRAINT CC_USUARIO_PROVIDER CHECK (tipo_provedor IN ('GOOGLE', 'FACEBOOK'))
);

CREATE TABLE categoria(
    id_categoria INT GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR2(255) NOT NULL,
    foto VARCHAR2(512) NOT NULL,
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (id_categoria)
);

CREATE TABLE usuario_categoria(
    id_usuario INT NOT NULL,
    id_categoria INT NOT NULL,
    CONSTRAINT FK_USUARIO_CATEGORIA_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT FK_USUARIO_CATEGORIA_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE post(
    id_post INT GENERATED ALWAYS AS IDENTITY,
    id_usuario INT NOT NULL,
    titulo VARCHAR2(128) NOT NULL,
    descricao VARCHAR2(128) NOT NULL,
    imagem_capa VARCHAR2(512),
    duracao_minutos INT NOT NULL,
    dificuldade INT NOT NULL,
    dica VARCHAR2(255),
    CONSTRAINT PK_POST PRIMARY KEY (id_post),
    CONSTRAINT FK_POST_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT CC_POST_DIFICULDADE CHECK (dificuldade BETWEEN 1 AND 5)
);

CREATE TABLE post_materiais(
    id_post INT NOT NULL,
    materiais VARCHAR2(128) NOT NULL,
    CONSTRAINT FK_POST_MATERIAIS FOREIGN KEY (id_post) REFERENCES post(id_post)
);

CREATE TABLE post_categoria(
    id_post INT NOT NULL,
    id_categoria INT NOT NULL,
    CONSTRAINT FK_POST_CATEGORIA_POST FOREIGN KEY (id_post) REFERENCES post(id_post),
    CONSTRAINT FK_POST_CATEGORIA_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE passo(
    id_passo INT GENERATED ALWAYS AS IDENTITY,
    id_post INT NOT NULL,
    texto VARCHAR2(512) NOT NULL,
    foto VARCHAR2(512),
    CONSTRAINT PK_PASSO PRIMARY KEY (id_passo),
    CONSTRAINT FK_PASSO_POST FOREIGN KEY (id_post) REFERENCES post(id_post)
);

CREATE TABLE comentario(	
    id_comentario INT GENERATED ALWAYS AS IDENTITY,
	id_post INT NOT NULL,
    id_usuario INT NOT NULL,
    texto VARCHAR2(512) NOT NULL,
    foto VARCHAR2(512),
	definicao VARCHAR2(12), 
    CONSTRAINT PK_COMENTARIO PRIMARY KEY (id_comentario),
    CONSTRAINT FK_COMENTARIO_POST FOREIGN KEY (id_post) REFERENCES post(id_post),
    CONSTRAINT FK_COMENTARIO_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario(id_usuario),   
    CONSTRAINT CC_COMENTARIO_DEFINICAO CHECK (definicao IN ('DUVIDA', 'ELOGIO', 'CONTRIBUICAO')) 
);

CREATE TABLE resposta(
    id_resposta INT GENERATED ALWAYS AS IDENTITY,
    id_comentario INT NOT NULL,
    id_usuario INT NOT NULL,
    texto VARCHAR2(512) NOT NULL,
    CONSTRAINT PK_RESPOSTA PRIMARY KEY (id_resposta),
    CONSTRAINT FK_RESPOSTA_COMENTARIO FOREIGN KEY (id_comentario) REFERENCES comentario(id_comentario), 
    CONSTRAINT FK_RESPOSTA_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE favorito(	
    id_usuario INT NOT NULL,
    id_post INT NOT NULL,
    CONSTRAINT FK_FAVORITO_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario(id_usuario),
    CONSTRAINT FK_FAVORITO_POST FOREIGN KEY (id_post) REFERENCES post(id_post)
);

CREATE TABLE feito(	
    id_feito INT GENERATED ALWAYS AS IDENTITY,
    id_post INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT FK_FEITO_POST FOREIGN KEY (id_post) REFERENCES post(id_post),
    CONSTRAINT FK_FEITO_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) 
);

CREATE TABLE lista(
    id_lista INT GENERATED ALWAYS AS IDENTITY,
    id_usuario INT NOT NULL,
    assunto VARCHAR2(128) NOT NULL,
    is_privado NUMBER(1) DEFAULT 0 NOT NULL,
    CONSTRAINT PK_LISTA PRIMARY KEY (id_lista),
    CONSTRAINT FK_LISTA_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario(id_usuario),
    CONSTRAINT CC_LISTA_IS_PRIVADO CHECK (is_privado IN (0, 1)) 
);

CREATE TABLE lista_post(
    id_lista INT NOT NULL,
    id_post INT NOT NULL,
    CONSTRAINT FK_LISTA_POST FOREIGN KEY (id_post) REFERENCES post(id_post),
    CONSTRAINT FK_POST_LISTA FOREIGN KEY (id_lista) REFERENCES lista(id_lista)
);

CREATE TABLE notificacao(
    id_notificacao INT GENERATED ALWAYS AS IDENTITY,
    id_post INT NOT NULL,
    id_usuario_a_notificar INT NOT NULL,
    id_usuario_da_interacao INT NOT NULL,
    id_comentario INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    visualizada NUMBER(1,0) NOT NULL,
    
    CONSTRAINT FK_NOTIFICACAO PRIMARY KEY (id_notificacao),
    CONSTRAINT FK_NOTIFICACAO_POST FOREIGN KEY (id_post) REFERENCES post(id_post),
    CONSTRAINT FK_NOTIFICACAO_USUARIO_A_NOTIFICAR FOREIGN KEY (id_usuario_a_notificar) REFERENCES usuario(id_usuario),
    CONSTRAINT FK_NOTIFICACAO_USUARIO_DA_INTERACAO FOREIGN KEY (id_usuario_da_interacao) REFERENCES usuario(id_usuario),
    CONSTRAINT FK_NOTIFICACAO_COMENTARIO FOREIGN KEY (id_comentario) REFERENCES comentario(id_comentario),
    CONSTRAINT CC_NOTIFICACAO_TIPO CHECK (tipo IN ('COMENTARIO', 'RESPOSTA')),
    CONSTRAINT CC_NOTIFICACAO_VISUALIZADA CHECK (visualizada IN (0, 1))
);

INSERT INTO categoria (nome, foto) VALUES ('desenho', 'https://i.ytimg.com/vi/_S0jCf6gSNc/hqdefault.jpg');
INSERT INTO categoria (nome, foto) VALUES ('moveis', 'https://media.gazetadopovo.com.br/2020/04/22180741/moveis-campo-largo-inaugura-ecommerce-2-660x372.jpg');
INSERT INTO categoria (nome, foto) VALUES ('casa', 'https://editoraolhares.com.br/janela/wp-content/uploads/2019/10/14_20190906_5292-HDR.jpg');
INSERT INTO categoria (nome, foto) VALUES ('tecnologia', 'https://enit.trabalho.gov.br/portal/images/Imagens/Institucional/Computador-FGTS2.jpg');
INSERT INTO categoria (nome, foto) VALUES ('comida', 'https://www.wikihow.com/images/thumb/7/74/Make-Sausage-Balls-Step-17.jpg/v4-728px-Make-Sausage-Balls-Step-17.jpg.webp');
INSERT INTO categoria (nome, foto) VALUES ('escola', 'https://www.wikihow.com/images/thumb/0/01/Get-Students%27-Attention-Step-1.jpg/v4-728px-Get-Students%27-Attention-Step-1.jpg.webp');
