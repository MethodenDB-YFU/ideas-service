CREATE SCHEMA yfu_ideas;

CREATE TYPE yfu_ideas.status AS enum (
	'DRAFT',
	'PUBLISHED',
	'INPROGRESS',
	'DONE'
);

CREATE TABLE yfu_ideas.ideas (
	i_id 			UUID		PRIMARY KEY,
	i_name			TEXT		NOT NULL,
	i_description	TEXT		NOT NULL,
	i_created_by	UUID		NOT NULL,
	i_status	yfu_ideas.status,
	i_created_at	TIMESTAMP	DEFAULT now()
);

CREATE TABLE yfu_ideas.likes (
  l_id        UUID    PRIMARY KEY,
	l_idea_id		UUID		REFERENCES yfu_ideas.ideas(i_id),
	l_like_by		UUID		NOT NULL,
	l_like_at		TIMESTAMP	DEFAULT now(),
	UNIQUE (l_idea_id, l_like_by)
);

CREATE TABLE yfu_ideas.comments (
  c_id        UUID    PRIMARY KEY,
	c_idea_id		UUID		REFERENCES yfu_ideas.ideas(i_id),
	c_comment		TEXT		NOT NULL,
	c_comment_by	UUID		NOT NULL,
	c_comment_at	TIMESTAMP	DEFAULT now()
);

CREATE TABLE yfu_ideas.commits (
  c_id        UUID    PRIMARY KEY,
	c_idea_id		UUID		REFERENCES yfu_ideas.ideas(i_id),
	c_commit_by	UUID		NOT NULL,
	c_commit_at	TIMESTAMP	DEFAULT now(),
	UNIQUE (c_idea_id, c_commit_by)
);

