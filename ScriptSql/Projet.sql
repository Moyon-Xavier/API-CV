Create Table Period(
    perno int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    Constraint pk_perno primary key (perno)
);

Create Table skills(
    skino int NOT NULL AUTO_INCREMENT,
    Title varchar(255),
    Constraint pk_skinoTitle primary key (skino,Title)
);


Create Table project(
    prono int NOT NULL AUTO_INCREMENT,
    projectName varchar(255),
    dateproject date,
    perno int,
    imageUrl varchar(600),
    description varchar(1200),

    Constraint pk_Projet primary key (prono),
    Constraint fk_period foreign key (perno)
        references period (perno) 
);

Create Table Requirement(
    skino int NOT NULL AUTO_INCREMENT,
    prono int,
    Constraint pk_ProjectSkill primary key(skino,prono),
     Constraint fk_project foreign key (prono)
        references project (prono),
     Constraint fk_skillRequirement foreign key (skino)
        references skills (skino)

)