create database Serralharia_Gabriel;

use Serralharia_Gabriel;

#13 / 11 / 2018 O mais atual


#drop database serralharia_gabriel;

#Tabela Produto
create table Produto(
	Cod_Produto int not null auto_increment,
    Descricao varchar(45),
    Quantidade int,
    Tamanho double(15,2),
    Medida varchar(15),
    Preco double(20,2),
    Preco_Revenda double(20,2),
    primary key(Cod_Produto)    
);

select * from produto;

#Tabela Usuário do sistema
create table Usuario_do_Sistema(
    Login_Usuario varchar(45) not null,
	Nome_Usuario varchar(50) not null,
    Senha varchar(25)not null,
	Restricao varchar(15) not null,
    primary key (Login_Usuario)
    );
   
select Nome_Usuario from Usuario_do_Sistema where Login_Usuario = 'IFRNAdmin';   
   

select * from Usuario_do_Sistema;

#Tabela cliente
create table Cliente(
	Cpf_Cliente char (14) not null,
    Nome_Cliente varchar (60) not null,
    Rua_Cliente varchar(60) not null,
	Numero_Cliente int(10) not null,
	Bairro_Cliente varchar(50) not null,
	Cidade_Cliente varchar(30) not null,
    Estado_Cliente varchar(50) not null,
    primary key (Cpf_Cliente),
	Tel_Cliente varchar(33)
 ); 

 
 #Tabela serviço
 create table Servico(
	Cod_Servico int not null auto_increment,
    Descricao_Servico varchar (100), 
	Cpf_Cliente char (14),
	Data_Inicio varchar(10),
    Data_Entrega varchar(10), 
	Pagamento varchar(30),
    Valor double(20,2),
	Servico_Status  varchar(15),
	mao_Obra double(20,2),
    QuantidadeM2 double(8,2),
	Login_Usuario varchar(45) not null,
	Lucro double(20,2),
    primary key (Cod_Servico), 
	foreign key (Cpf_Cliente) references  Cliente(Cpf_Cliente),
 	foreign key (Login_Usuario) references Usuario_do_Sistema(Login_Usuario)
 );
 
 select * from servico_has_produto;
 
 #Tabela tem Produto
 create table Servico_Has_Produto(
	Cod_Servico int not null,
    Cod_Produto int,
	Descricao varchar(45),
    Quantidade int  ,
    Tamanho double(15,2),
    Medida varchar(15),
    Preco double(20,2),
    Preco_Revenda double(20,2),
	primary key (Cod_Servico, Cod_Produto),
    foreign key (Cod_servico) references Servico(Cod_Servico),
    foreign key (Cod_Produto) references Produto(Cod_Produto)
 );
 
 #Essa tabela serve para cadastrar novas os la na tela de adiconar uma nova os
 create table OS(
	ordemX int not null auto_increment primary key,
    ordemXNome varchar(255) not null
 );
 
 #drop table OS;
 
 #Essa Tabela serve pra criar as os ja predefinidas e lista-las na tela de add OS
 insert into OS (ordemXNome)
 values ('...'), ('Portão Basculante'), ('Portão Deslizante'), ('Portão Pivotante'),
		('Porta de Aço'), ('Porta de Aço Automática'), ('Escada'), ('Corrimão'),
		('Grade para Janela'), ('Janela de Ferro'), ('Porta Ferro');
 
 select * from OS where ordemX > 1 order by ordemXNome;
 
 delete from OS where ordemX = 13;
 
insert into Usuario_do_Sistema (Login_Usuario,Nome_Usuario, Senha,Restricao) values ('lucedioIFRN','Lucédio Candido','000000','Administrador');


insert into Usuario_do_Sistema( Login_Usuario, Nome_Usuario, Senha, Restricao)
						values('brunoMezenga', 'Bruno Alves', '000000', 'Administrador');

insert into Usuario_do_Sistema( Login_Usuario, Nome_Usuario, Senha, Restricao)
						values('IFRNAdmin', 'IFRN Teste', '000000', 'Administrador');
                        

select * from usuario_do_sistema;



select * from OS;

select * from servico;



select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Cliente.Rua_Cliente, Cliente.Numero_Cliente, Cliente.Rua_Cliente,
                Cliente.Cidade_Cliente, Cliente.Bairro_Cliente, Cliente.Estado_Cliente, Cliente.Tel_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,
                Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.QuantidadeM2, Servico.Lucro, Usuario_do_Sistema.Nome_Usuario
                from Servico, Cliente, Usuario_do_Sistema
                where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario
                and Cliente.Cpf_Cliente ='585.202.021-20';
        