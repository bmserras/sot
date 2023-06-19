create table widget
(
    identifier varchar,
    name varchar,
    type varchar,
    primary key (identifier)
);


create table radar
(
    identifier varchar,
    ip_address varchar,
    primary key (identifier),
    foreign key (identifier) references widget (identifier)
);


/*

 Existem dispositivos (device)
 Widget está associado a um dispositivo


 */

