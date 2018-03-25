-- 权限表 --
CREATE TABLE permission(
  pid INT (11) NOT NULL auto_increment,
  name VARCHAR (255) NOT NULL DEFAULT '',
  url VARCHAR (255) DEFAULT '',
  PRIMARY KEY (pid)
)engine = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO permission VALUES ('1','add','');
INSERT INTO permission VALUES ('2','delete','');
INSERT INTO permission VALUES ('3','edit','');
INSERT INTO permission VALUES ('4','query','');

-- 用户表 --
 create table user (
  uid INT (11) NOT NULL auto_increment,
  username VARCHAR (255) NOT NULL DEFAULT '',
  password VARCHAR (255) NOT NULL DEFAULT '',
  PRIMARY KEY (uid)
)engine = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO user VALUES ('1','admin','123');
INSERT INTO user VALUES ('2','demo','123');

-- 角色表--
create TABLE role(
  rid INT (11) NOT NULL auto_increment,
  rname VARCHAR (255) NOT NULL DEFAULT '',
  PRIMARY KEY (rid)
)engine = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO role VALUES ('1','admin');
INSERT INTO role VALUES ('2','customer');

-- 权限角色关系表--
CREATE TABLE permission_role(
  rid INT (11)  NOT NULL ,
  pid INT (11)  NOT NULL ,
  KEY idx_rid(rid),
  KEY idx_pid(pid)
)engine = InnoDB DEFAULT CHARSET = utf8;
INSERT permission_role VALUES ('1','1');
INSERT permission_role VALUES ('1','2');
INSERT permission_role VALUES ('1','3');
INSERT permission_role VALUES ('1','4');
INSERT permission_role VALUES ('2','1');
INSERT permission_role VALUES ('2','4');


-- 用户角色关系表
CREATE TABLE user_role(
  rid INT (11)  NOT NULL ,
  uid INT (11)  NOT NULL ,
  KEY idx_rid(rid),
  KEY idx_uid(uid)
)engine = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO user_role VALUES ('1','1');
INSERT INTO user_role VALUES ('2','2');