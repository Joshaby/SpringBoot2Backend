
    create table tb_categorias (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_categorias_produtos (
       categoria_id integer not null,
        produto_id integer not null,
        primary key (categoria_id, produto_id)
    ) engine=InnoDB;

    create table tb_cidades (
       id integer not null auto_increment,
        nome varchar(255),
        estado_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_clientes (
       id integer not null auto_increment,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo_cliente integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_enderecos (
       id integer not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        cidade_id integer,
        cliente_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_estados (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_item_pedido (
       desconto double precision,
        preco double precision,
        quantidade integer,
        produto_id integer not null,
        pedido_id integer not null,
        primary key (pedido_id, produto_id)
    ) engine=InnoDB;

    create table tb_pagamentos (
       pedido_id integer not null,
        estado_pagamento integer,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pagamentos_cartao (
       numero_de_parcelas integer,
        pedido_id integer not null,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pagamentos_com_boleto (
       data_pagamento datetime,
        data_vencimento datetime,
        pedido_id integer not null,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pedidos (
       id integer not null auto_increment,
        instante datetime,
        cliente_id integer,
        endereco_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_produtos (
       id integer not null auto_increment,
        nome varchar(255),
        preco double precision,
        primary key (id)
    ) engine=InnoDB;

    create table tb_telefones (
       cliente_id integer not null,
        telefones varchar(255)
    ) engine=InnoDB;

    alter table tb_clientes 
       add constraint UK_9o6dwdqix5udt21xn0tsq4m7m unique (email);

    alter table tb_categorias_produtos 
       add constraint FKdxe8cytqvvmontixjwkwc6jew 
       foreign key (produto_id) 
       references tb_produtos (id);

    alter table tb_categorias_produtos 
       add constraint FKnb8v9cijxpp2odwjs59k034kl 
       foreign key (categoria_id) 
       references tb_categorias (id);

    alter table tb_cidades 
       add constraint FKqoy3fxe4vvhaaf5qsw3dtbag7 
       foreign key (estado_id) 
       references tb_estados (id);

    alter table tb_enderecos 
       add constraint FKd1w4po9uu0x5jyjii5hhgvq01 
       foreign key (cidade_id) 
       references tb_cidades (id);

    alter table tb_enderecos 
       add constraint FKtjgooj5176dkbo5n6qv493jxh 
       foreign key (cliente_id) 
       references tb_clientes (id);

    alter table tb_item_pedido 
       add constraint FKnpevrq6vpcfcnj4gubf1809ve 
       foreign key (produto_id) 
       references tb_produtos (id);

    alter table tb_item_pedido 
       add constraint FK84972dyukk617aqp62nvxpj5s 
       foreign key (pedido_id) 
       references tb_pedidos (id);

    alter table tb_pagamentos 
       add constraint FKcjlb736jmhj6o7goj41e19796 
       foreign key (pedido_id) 
       references tb_pedidos (id);

    alter table tb_pagamentos_cartao 
       add constraint FKl83s5vqu453qv13ke0s2slldt 
       foreign key (pedido_id) 
       references tb_pagamentos (pedido_id);

    alter table tb_pagamentos_com_boleto 
       add constraint FKj9qly1s43ll46yl3e946serxn 
       foreign key (pedido_id) 
       references tb_pagamentos (pedido_id);

    alter table tb_pedidos 
       add constraint FKjc16u7dot8y3iengmehtvhq46 
       foreign key (cliente_id) 
       references tb_clientes (id);

    alter table tb_pedidos 
       add constraint FKpehiftpmax0ndkfti2g9xfvba 
       foreign key (endereco_id) 
       references tb_enderecos (id);

    alter table tb_telefones 
       add constraint FK7bbjm8bg83814bse304d1w4du 
       foreign key (cliente_id) 
       references tb_clientes (id);

    create table tb_categorias (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_categorias_produtos (
       categoria_id integer not null,
        produto_id integer not null,
        primary key (categoria_id, produto_id)
    ) engine=InnoDB;

    create table tb_cidades (
       id integer not null auto_increment,
        nome varchar(255),
        estado_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_clientes (
       id integer not null auto_increment,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo_cliente integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_enderecos (
       id integer not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        cidade_id integer,
        cliente_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_estados (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_item_pedido (
       desconto double precision,
        preco double precision,
        quantidade integer,
        produto_id integer not null,
        pedido_id integer not null,
        primary key (pedido_id, produto_id)
    ) engine=InnoDB;

    create table tb_pagamentos (
       pedido_id integer not null,
        estado_pagamento integer,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pagamentos_cartao (
       numero_de_parcelas integer,
        pedido_id integer not null,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pagamentos_com_boleto (
       data_pagamento datetime,
        data_vencimento datetime,
        pedido_id integer not null,
        primary key (pedido_id)
    ) engine=InnoDB;

    create table tb_pedidos (
       id integer not null auto_increment,
        instante datetime,
        cliente_id integer,
        endereco_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_produtos (
       id integer not null auto_increment,
        nome varchar(255),
        preco double precision,
        primary key (id)
    ) engine=InnoDB;

    create table tb_telefones (
       cliente_id integer not null,
        telefones varchar(255)
    ) engine=InnoDB;

    alter table tb_clientes 
       add constraint UK_9o6dwdqix5udt21xn0tsq4m7m unique (email);

    alter table tb_categorias_produtos 
       add constraint FKdxe8cytqvvmontixjwkwc6jew 
       foreign key (produto_id) 
       references tb_produtos (id);

    alter table tb_categorias_produtos 
       add constraint FKnb8v9cijxpp2odwjs59k034kl 
       foreign key (categoria_id) 
       references tb_categorias (id);

    alter table tb_cidades 
       add constraint FKqoy3fxe4vvhaaf5qsw3dtbag7 
       foreign key (estado_id) 
       references tb_estados (id);

    alter table tb_enderecos 
       add constraint FKd1w4po9uu0x5jyjii5hhgvq01 
       foreign key (cidade_id) 
       references tb_cidades (id);

    alter table tb_enderecos 
       add constraint FKtjgooj5176dkbo5n6qv493jxh 
       foreign key (cliente_id) 
       references tb_clientes (id);

    alter table tb_item_pedido 
       add constraint FKnpevrq6vpcfcnj4gubf1809ve 
       foreign key (produto_id) 
       references tb_produtos (id);

    alter table tb_item_pedido 
       add constraint FK84972dyukk617aqp62nvxpj5s 
       foreign key (pedido_id) 
       references tb_pedidos (id);

    alter table tb_pagamentos 
       add constraint FKcjlb736jmhj6o7goj41e19796 
       foreign key (pedido_id) 
       references tb_pedidos (id);

    alter table tb_pagamentos_cartao 
       add constraint FKl83s5vqu453qv13ke0s2slldt 
       foreign key (pedido_id) 
       references tb_pagamentos (pedido_id);

    alter table tb_pagamentos_com_boleto 
       add constraint FKj9qly1s43ll46yl3e946serxn 
       foreign key (pedido_id) 
       references tb_pagamentos (pedido_id);

    alter table tb_pedidos 
       add constraint FKjc16u7dot8y3iengmehtvhq46 
       foreign key (cliente_id) 
       references tb_clientes (id);

    alter table tb_pedidos 
       add constraint FKpehiftpmax0ndkfti2g9xfvba 
       foreign key (endereco_id) 
       references tb_enderecos (id);

    alter table tb_telefones 
       add constraint FK7bbjm8bg83814bse304d1w4du 
       foreign key (cliente_id) 
       references tb_clientes (id);
