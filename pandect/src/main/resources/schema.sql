create table if not exists pandect
(
    id          bigint primary key auto_increment,
    type        integer     not null,
    title       varchar(64) not null,
    description varchar(255) default '',
    author      varchar(64)  default '',
    cover       varchar(255) default '',
    status      integer     not null,
    created_at  timestamp   not null,
    updated_at  timestamp    default current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

create table if not exists chapter
(
    id         bigint primary key auto_increment,
    pandect_id bigint    not null,
    title      varchar(64) default '',
    created_at timestamp not null,
    updated_at timestamp   default current_timestamp,
    sequence   integer     default 0,
    constraint unique uk_1 (pandect_id, title),
    index idx_1 (pandect_id, sequence)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

create table if not exists section
(
    id         bigint primary key auto_increment,
    title      varchar(64) default '',
    pandect_id bigint    not null,
    chapter_id bigint    not null,
    sequence   integer     default 0,
    content    text      not null,
    created_at timestamp not null,
    updated_at timestamp   default current_timestamp,
    version    bigint      default 0,
    index idx_1 (pandect_id, chapter_id, sequence)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

create table if not exists annotation
(
    id              bigint primary key auto_increment,
    section_id      bigint    not null,
    section_version bigint    not null,
    pronounce       text      not null,
    content_marks   text      not null,
    created_at      timestamp not null,
    updated_at      timestamp default current_timestamp,
    version         bigint    default 0,
    index idx_1 (section_id, section_version)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;