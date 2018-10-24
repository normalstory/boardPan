/* 게시판 */
CREATE TABLE boardPan (
	PanId VARCHAR2(50) NOT NULL, /* 게시판 ID */
	PanName VARCHAR2(50), /* 게시판 이름 */
	PanDel CHAR(1) NOT NULL, /* 게시판 사용여부 */
	PanWriter VARCHAR2(50) NOT NULL, /* 게시판 등록자 */
	PanBirth DATE /* 게시판 등록일 */
);

COMMENT ON TABLE boardPan IS '게시판';

COMMENT ON COLUMN boardPan.PanId IS '게시판 ID';

COMMENT ON COLUMN boardPan.PanName IS '게시판 이름';

COMMENT ON COLUMN boardPan.PanDel IS '게시판 사용여부';

COMMENT ON COLUMN boardPan.PanWriter IS '게시판 등록자';

COMMENT ON COLUMN boardPan.PanBirth IS '게시판 등록일';

CREATE UNIQUE INDEX PK_boardPan
	ON boardPan (
		PanId ASC
	);

ALTER TABLE boardPan
	ADD
		CONSTRAINT PK_boardPan
		PRIMARY KEY (
			PanId
		);

/* 게시글 */
CREATE TABLE boardText (
	TextNum INTEGER NOT NULL, /* 게시글번호 */
	PanId VARCHAR2(50) NOT NULL, /* 게시판 ID */
	TextName VARCHAR2(255), /* 제목 */
	TextSub CLOB, /* 내용 */
	TextWriterId VARCHAR2(50) NOT NULL, /* 작성자ID */
	TextWriteDate DATE, /* 작성일시 */
	TextDel CHAR(1) NOT NULL, /* 삭제여부 */
	TextDelDate DATE, /* 삭제일시 */
	SeqNum NUMBER, /* 시퀀스NO */
	TextNumP INTEGER /* 부모게시글번호 */
);

COMMENT ON TABLE boardText IS '게시글';

COMMENT ON COLUMN boardText.TextNum IS '게시글번호';

COMMENT ON COLUMN boardText.PanId IS '게시판 ID';

COMMENT ON COLUMN boardText.TextName IS '제목';

COMMENT ON COLUMN boardText.TextSub IS '내용';

COMMENT ON COLUMN boardText.TextWriterId IS '작성자ID';

COMMENT ON COLUMN boardText.TextWriteDate IS '작성일시';

COMMENT ON COLUMN boardText.TextDel IS '삭제여부';

COMMENT ON COLUMN boardText.TextDelDate IS '삭제일시';

COMMENT ON COLUMN boardText.SeqNum IS '시퀀스NO';

COMMENT ON COLUMN boardText.TextNumP IS '부모게시글번호';

CREATE UNIQUE INDEX PK_boardText
	ON boardText (
		TextNum ASC
	);

ALTER TABLE boardText
	ADD
		CONSTRAINT PK_boardText
		PRIMARY KEY (
			TextNum
		);

/* 첨부파일 */
CREATE TABLE addFile (
	AddFileNum INTEGER NOT NULL, /* 첨부파일번호 */
	TextNum INTEGER NOT NULL, /* 게시글번호 */
	AddFileUrl VARCHAR2(500) /* 첨부파일 */
);

COMMENT ON TABLE addFile IS '첨부파일';

COMMENT ON COLUMN addFile.AddFileNum IS '첨부파일번호';

COMMENT ON COLUMN addFile.TextNum IS '게시글번호';

COMMENT ON COLUMN addFile.AddFileUrl IS '첨부파일';

CREATE UNIQUE INDEX PK_addFile
	ON addFile (
		AddFileNum ASC
	);

ALTER TABLE addFile
	ADD
		CONSTRAINT PK_addFile
		PRIMARY KEY (
			AddFileNum
		);

/* 댓글 */
CREATE TABLE boardReply (
	ReplyID VARCHAR2(50) NOT NULL, /* 댓글ID */
	TextNum INTEGER NOT NULL, /* 게시글번호 */
	ReplySub VARCHAR2(500), /* 댓글내용 */
	ReplyerId VARCHAR2(50), /* 댓글작성자ID */
	ReplyDate DATE, /* 댓글작성일시 */
	ReplyDel CHAR(1) NOT NULL, /* 삭제여부 */
	ReplyDelDate DATE /* 삭제일시 */
);

COMMENT ON TABLE boardReply IS '댓글';

COMMENT ON COLUMN boardReply.ReplyID IS '댓글ID';

COMMENT ON COLUMN boardReply.TextNum IS '게시글번호';

COMMENT ON COLUMN boardReply.ReplySub IS '댓글내용';

COMMENT ON COLUMN boardReply.ReplyerId IS '댓글작성자ID';

COMMENT ON COLUMN boardReply.ReplyDate IS '댓글작성일시';

COMMENT ON COLUMN boardReply.ReplyDel IS '삭제여부';

COMMENT ON COLUMN boardReply.ReplyDelDate IS '삭제일시';

CREATE UNIQUE INDEX PK_boardReply
	ON boardReply (
		ReplyID ASC
	);

ALTER TABLE boardReply
	ADD
		CONSTRAINT PK_boardReply
		PRIMARY KEY (
			ReplyID
		);

ALTER TABLE boardText
	ADD
		CONSTRAINT FK_boardPan_TO_boardText
		FOREIGN KEY (
			PanId
		)
		REFERENCES boardPan (
			PanId
		);

ALTER TABLE boardText
	ADD
		CONSTRAINT FK_boardText_TO_boardText
		FOREIGN KEY (
			TextNumP
		)
		REFERENCES boardText (
			TextNum
		);

ALTER TABLE addFile
	ADD
		CONSTRAINT FK_boardText_TO_addFile
		FOREIGN KEY (
			TextNum
		)
		REFERENCES boardText (
			TextNum
		);

ALTER TABLE boardReply
	ADD
		CONSTRAINT FK_boardText_TO_boardReply
		FOREIGN KEY (
			TextNum
		)
		REFERENCES boardText (
			TextNum
		);
        
        commit;


---------
insert into boardPan values ('test1','첫판','n','brown',to_date('20180214','yyyy-MM-dd'));  
insert into boardPan values ('test7','칠째판','y','jesica',to_date('20180214','yyyy-MM-dd')); 
insert into boardPan values ('test3','세째판','y','brown',to_date('20180214','yyyy-MM-dd')); 
insert into boardPan values ('test4','네째판','y','brown',to_date('20180214','yyyy-MM-dd'));  
commit;

--------
select * from boardPan;
select * from boardPan where PanDel='y';

--------
--insert into boardPan values ('test4','네째판','y','brown',to_date('20180214','yyyy-MM-dd')); 
--insert into boardPan values (${PanId} ${PanName} ${PanDel} ${PanWriter} ${PanBirth})
--시퀀스 만들기
create SEQUENCE PanId
    start with 1  --보통 1로 함
    increment by 1; --보통 1--시퀀스 만들기
--오늘 날짜 TO_CHAR(SYSDATE,'YYYY-MM-DD')
--insert into boardPan values (PanId.nextVal,${PanName}, ${PanDel}, ${PanWriter},TO_Date(SYSDATE,'YYYY-MM-DD HH:mm'));
insert into boardPan values 
(PanId.nextVal, '하이', 'y', 'brown', TO_Date(SYSDATE,'YYYY-MM-DD'));


commit;

select * from boardPan where PanId='test2';

---------------------------------------------------------------------------------------------

select * from boardPan;

insert into boardText 
values( TextNum, PanId, TextName, TextSub, 
        TextWriterId, TextWriteDate,TextDel,
        TextDelDate, SeqNum, TextNumP); 

create SEQUENCE TextNum
    start with 3  --보통 1로 함
    increment by 1; --보통 1--시퀀스 만들기
    
insert into boardText values( 
1, 'testA21', '로그인 개발 01', '로그인 개발 관련 내용', 'jesica', SYSDATE, 'n',null ,null,null); 
    insert into boardText values(
    TextNum.nextVal+2, 'testA21', '로그인 개발 관련질문', '로그인 개발 관련 질문 내용입니다', 'jesica', SYSDATE, 'n',null ,null,1);

insert into boardText values( 
2, 'testA21', '로그인 개발 02', '로그인 개발 관련 내용2', 'jesica', SYSDATE, 'n',null ,null,null); 
    insert into boardText values( TextNum.nextVal+2, 'testA21', '> 안녕하세요, 49번 글 질문있습니다', '안녕하세요 ^^ 49번 로그인 개발 관련 내용 질문입니다 ', 'jesica', SYSDATE, 'n',null ,null,81); 
    insert into boardText values( TextNum.nextVal+2, 'testA21', '> 저도 50번 내용 질문있어요', '저도 50번 로그인 개발 관련 내용 질문입니다', 'jesica', SYSDATE, 'n',null ,null,85);
    insert into boardText values( TextNum.nextVal+2, 'testA21', '>> 회신이 늦었습니다.', '늦었지만 로그인 개발관련 답변을 남깁니다', 'jesica', SYSDATE, 'n',null ,null,87);
    insert into boardText values( TextNum.nextVal+2, 'testA21', '>>> 답변 쌩유베리 다시셀프 감사입니다', '답변 고맙습니다라는 내용입니다 ', 'jesica', SYSDATE, 'n',null ,null,88); 

insert into boardText values( 
TextNum.nextVal+2, 'testA25', '메인페이지 개발 03', '메인페이지 개발 관련 내용3', 'jesica', SYSDATE, 'n',null ,null,null); 

insert into boardText values( 
TextNum.nextVal+2, 'testA22', '게시판관리 개발 03', '게시판관리 개발 관련 내용3', 'jesica', SYSDATE, 'n',null ,null,null); 

select * from boardText order by textnum;

commit;

select * from boardText where PanId='testA21';
select count(*) from boardText where PanId='testA21' and TextDel='n'; 

--선택목록 + 페이징처리 
select * from
        (select rownum rnum, a.* from
                (select boardText.* from boardText 
                 where PanId='testA21' and TextDel='n' order by textNum desc) a)     
        where rnum between 1*10-(10-1)
		               and 1*10;

--선택목록 + 페이징처리(10) + 정렬
select textnum, lpad('> ',(level-1)*3, ' ')|| textname, textWriterId, textWriteDate, textnump, level  
from
        (select rownum rnum, a.* 
        from
                (select boardText.* from boardText 
                 where PanId='testA21' and TextDel='n' order by textNum desc) a)     
        where rnum between 1*10-(10-1)
		               and 1*10
    start with textnump is null 
    connect by prior textnum = textnump
    order siblings by textnum desc ;

--선택목록 + 페이징처리 + 정렬 + java
select textnum, lpad('> ',(level-1)*3, ' ')|| textname, textWriterId, textWriteDate, textnump,SeqNum, level 
from
        (select rownum rnum, a.* 
        from
            (select boardText.* from boardText 
             where TextDel='n' order by textNum desc) a)     
        where rnum between 1*10-(10-1)
		               and 1*10
    start with textnump is null 
    connect by prior textnum = textnump
    order siblings by SeqNum desc,textnum asc ;
    
    
    
    


--글번호,제목,작성자아이디,작성일시
select textnum, lpad('> ',(level-1)*3, ' ')|| textname text, textWriterId, textWriteDate, textnump, level 
    from boardText
    start with textnump is null 
    connect by prior textnum = textnump
    order siblings by textnum desc;
    
    
--------
select lpad('> ',(level-1)*3, ' ')|| title, board.*, level 
    from board
    start with pid is null 
    connect by prior id = pid
    order siblings by id desc;

select textnum, lpad('> ',(level-1)*3,' ')||textname, textWriterId, textWriteDate, textnump, level 
    from boardText
    start with textnump is null 
    connect by prior textnum = textnump
    order siblings by textnum desc;
---------
    
    
select * from boardText where TextNum=89;

select * from boardText where PanId='testA21' and TextDel='n' order by textNum desc;

select * from boardText; 

select TextNum.nextVal+2 from dual;
select TextNum.currval+2 from dual;
select AddFileNum.nextVal from dual;
select AddFileNum.currval from dual;
--------
select * from addFile;
select * from addFile where textNum=246;


create SEQUENCE AddFileNum
    start with 1  --보통 1로 함
    increment by 1; --보통 1--시퀀스 만들기


insert into addFile values( AddFileNum.nextVal, TextNum.currval+2, 'a');
select * from addFile;


ALTER TABLE addFile 
ADD( AddFileName varchar2(100) );



create SEQUENCE ReplyID
    start with 1  --보통 1로 함
    increment by 1; --보통 1--시퀀스 만들기
    

create SEQUENCE SeqNum
    start with 1  --보통 1로 함
    increment by 1; --보통 1--시퀀스 만들기


select * from boardReply;
select * from boardReply where TextNum=177;

--                            ReplyID.nextVal, ${textNum}, ${replySub}, ${replyerId},   sysdate, 'n', null
insert into boardReply values(ReplyID.nextVal, 178,         a,           jesica,        SYSDATE,  n, null);
insert into boardReply values(ReplyID.nextVal, 178,        'a',         'jesica',       SYSDATE, 'n', null);

update boardReply set
		replyDel='y'
		where
		ReplyId=1;


update boardText set
        seqNum = 0;
        
commit