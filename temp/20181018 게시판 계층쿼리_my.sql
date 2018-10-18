/* �Խñ� */
CREATE TABLE board (
	id NUMBER NOT NULL, /* �Խñ۹�ȣ */
	title varchar2(500), /* ���� */
	pid NUMBER /* �θ�Խñ۹�ȣ */
);

COMMENT ON TABLE board IS '�Խñ�';

COMMENT ON COLUMN board.id IS '�Խñ۹�ȣ';

COMMENT ON COLUMN board.title IS '����';

COMMENT ON COLUMN board.pid IS '�θ�Խñ۹�ȣ';

CREATE UNIQUE INDEX PK_board
	ON board (
		id ASC
	);

ALTER TABLE board
	ADD
		CONSTRAINT PK_board
		PRIMARY KEY (
			id
		);

ALTER TABLE board
	ADD
		CONSTRAINT FK_board_TO_board
		FOREIGN KEY (
			pid
		)
		REFERENCES board (
			id
		);
        

insert into board values (1, 'ù��° ��', '');        
insert into board values (2, '�ι�° ��', '');        
insert into board values (3, '����° ��-2���� ���', 2);        
insert into board values (4, '�׹�° ��-3���� ���', 3);        
insert into board values (5, '�ټ���° ��', '');        
insert into board values (6, '������° ��', '');        
insert into board values (7, '�ϰ���° ��-5����  ���', 5);        
insert into board values (8, '������° ��-2����  ���', 2);

commit;


select * from board;

drop table board;
commit;


select lpad('�� ',(level-1)*3, ' ')|| title, board.*, level 
    from board
    start with pid is null 
    connect by prior id = pid
    order siblings by id desc;
    