CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	role_code varchar(5) NOT NULL,
	role_name varchar(30) NOT NULL,
	created_by varchar(36),
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE roles ADD CONSTRAINT role_pk PRIMARY KEY(id);
ALTER TABLE roles ADD CONSTRAINT role_bk UNIQUE(role_code);

CREATE TABLE users(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	email varchar(50) NOT NULL,
	"password" varchar(225) NOT NULL,
	verification_code varchar(5) NOT NULL,
	role_id varchar(36) NOT NULL,
	created_by varchar(36),
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY(id);
ALTER TABLE users ADD CONSTRAINT user_bk UNIQUE(email);
ALTER TABLE users ADD CONSTRAINT role_fk FOREIGN KEY(role_id) REFERENCES roles(id);

CREATE TABLE industries(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	industry_code varchar(5) NOT NULL,
	indsutry_name varchar(100) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE industries ADD CONSTRAINT industry_pk PRIMARY KEY(id);
ALTER TABLE industries ADD CONSTRAINT industry_bk UNIQUE(industry_code);

CREATE TABLE positions(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	position_code varchar(5) NOT NULL,
	position_name varchar(100) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE positions ADD CONSTRAINT position_pk PRIMARY KEY(id);
ALTER TABLE positions ADD CONSTRAINT position_bk UNIQUE(position_code);

CREATE TABLE provinces(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	province_code varchar(5) NOT NULL,
	province_name varchar(50) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE provinces ADD CONSTRAINT province_pk PRIMARY KEY(id);
ALTER TABLE provinces ADD CONSTRAINT province_bk UNIQUE(province_code);

CREATE TABLE regencies(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	regency_code varchar(5) NOT NULL,
	regency_name varchar(50) NOT NULL,
	province_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE regencies ADD CONSTRAINT regency_pk PRIMARY KEY(id);
ALTER TABLE regencies ADD CONSTRAINT regency_bk UNIQUE(regency_code);
ALTER TABLE regencies ADD CONSTRAINT regency_province_fk FOREIGN KEY(province_id) REFERENCES provinces(id);

CREATE TABLE attachments(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	attachment_code varchar(5) NOT NULL,
	attachment_content bytea NOT NULL,
	attachment_extension varchar(10) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE attachments ADD CONSTRAINT attachment_pk PRIMARY KEY(id);
ALTER TABLE attachments ADD CONSTRAINT attachment_bk UNIQUE(attachment_code);

CREATE TABLE profiles(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	profile_code varchar(5) NOT NULL,
	profile_name varchar(100) NOT NULL,
	profile_company varchar(100) NOT NULL,
	profile_image varchar(36),
	user_id varchar(36) NOT NULL,
	industry_id varchar(36) NOT NULL,
	position_id varchar(36) NOT NULL,
	province_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE profiles ADD CONSTRAINT profile_pk PRIMARY KEY(id);
ALTER TABLE profiles ADD CONSTRAINT profile_bk UNIQUE(profile_code);
ALTER TABLE profiles ADD CONSTRAINT profile_image_fk FOREIGN KEY(profile_image) REFERENCES attachments(id);
ALTER TABLE profiles ADD CONSTRAINT profile_user_fk FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE profiles ADD CONSTRAINT profile_industry_fk FOREIGN KEY(industry_id) REFERENCES industries(id);
ALTER TABLE profiles ADD CONSTRAINT profile_position_fk FOREIGN KEY(position_id) REFERENCES positions(id);
ALTER TABLE profiles ADD CONSTRAINT profile_province_fk FOREIGN KEY(province_id) REFERENCES provinces(id);

CREATE TABLE categories(
	id varchar(36) DEFAULT uuid_generate_v4(),
	category_code varchar(5) NOT NULL,
	category_name varchar(100) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE categories ADD CONSTRAINT category_pk PRIMARY KEY(id);
ALTER TABLE categories ADD CONSTRAINT category_bk UNIQUE(category_code);

CREATE TABLE thread_types(
	id varchar(36) DEFAULT uuid_generate_v4(),
	type_code varchar(5) NOT NULL,
	type_name varchar(50) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE thread_types ADD CONSTRAINT type_pk PRIMARY KEY(id);
ALTER TABLE thread_types ADD CONSTRAINT type_bk UNIQUE(type_code);

CREATE TABLE threads(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_code varchar(5) NOT NULL,
	thread_title varchar(100) NOT NULL,
	thread_content text,
	is_premium boolean,
	type_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE threads ADD CONSTRAINT thread_pk PRIMARY KEY(id);
ALTER TABLE threads ADD CONSTRAINT thread_bk UNIQUE(thread_code);
ALTER TABLE threads ADD CONSTRAINT thread_type_fk FOREIGN KEY(type_id) REFERENCES thread_types(id);

CREATE TABLE thread_categories(
	id varchar(36) DEFAULT uuid_generate_v4(),
	category_id varchar(36),
	thread_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE thread_categories ADD CONSTRAINT thread_category_pk PRIMARY KEY(id);
ALTER TABLE thread_categories ADD CONSTRAINT category_fk FOREIGN KEY(category_id) REFERENCES categories(id);
ALTER TABLE thread_categories ADD CONSTRAINT thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);

CREATE TABLE thread_attachment(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_id varchar(36),
	attachment_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE thread_attachment ADD CONSTRAINT thread_attachment_pk PRIMARY KEY(id);
ALTER TABLE thread_attachment ADD CONSTRAINT thread_attachment_ck UNIQUE(thread_id, attachment_id);
ALTER TABLE thread_attachment ADD CONSTRAINT thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);
ALTER TABLE thread_attachment ADD CONSTRAINT attachment_fk FOREIGN KEY(attachment_id) REFERENCES attachments(id);

CREATE TABLE thread_comments(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	comment_code varchar(5) NOT NULL,
	comment_content text,
	thread_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE thread_comments ADD CONSTRAINT comment_pk PRIMARY KEY(id);
ALTER TABLE thread_comments ADD CONSTRAINT comment_bk UNIQUE(comment_code);
ALTER TABLE thread_comments ADD CONSTRAINT comment_thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);

CREATE TABLE thread_like(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	like_code varchar(5) NOT NULL,
	thread_id varchar(36)NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE thread_like ADD CONSTRAINT like_pk PRIMARY KEY(id);
ALTER TABLE thread_like ADD CONSTRAINT like_bk UNIQUE(like_code);
ALTER TABLE thread_like ADD CONSTRAINT like_thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);

CREATE TABLE bookmarks(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	bookmark_code varchar(5) NOT NULL,
	thread_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE bookmarks ADD CONSTRAINT bookmark_pk PRIMARY KEY(id);
ALTER TABLE bookmarks ADD CONSTRAINT bookmark_bk UNIQUE(bookmark_code);
ALTER TABLE bookmarks ADD CONSTRAINT bookmark_thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);

CREATE TABLE pollings(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	polling_code varchar(5) NOT NULL,
	polling_name varchar(100) NOT NULL,
	thread_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE pollings ADD CONSTRAINT polling_pk PRIMARY KEY(id);
ALTER TABLE pollings ADD CONSTRAINT polling_bk UNIQUE(polling_code);
ALTER TABLE pollings ADD CONSTRAINT polling_thread_fk FOREIGN KEY(thread_id) REFERENCES threads(id);

CREATE TABLE polling_choices(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	choice_code varchar(5) NOT NULL,
	choice_name varchar(100) NOT NULL,
	polling_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE polling_choices ADD CONSTRAINT choice_pk PRIMARY KEY(id);
ALTER TABLE polling_choices ADD CONSTRAINT choice_bk UNIQUE(choice_code);
ALTER TABLE polling_choices ADD CONSTRAINT choice_polling_fk FOREIGN KEY(polling_id) REFERENCES pollings(id);

CREATE TABLE choice_votes(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	vote_code varchar(5) NOT NULL,
	choice_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE choice_votes ADD CONSTRAINT vote_pk PRIMARY KEY(id);
ALTER TABLE choice_votes ADD CONSTRAINT vote_bk UNIQUE(vote_code);
ALTER TABLE choice_votes ADD CONSTRAINT vote_choice_fk FOREIGN KEY(choice_id) REFERENCES polling_choices(id);

CREATE TABLE event_types(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	type_code varchar(5) NOT NULL,
	type_name varchar(50) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE event_types ADD CONSTRAINT type_event_pk PRIMARY KEY(id);
ALTER TABLE event_types ADD CONSTRAINT type_event_bk UNIQUE(type_code);

CREATE TABLE payment_method(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	payment_code varchar(5) NOT NULL,
	payment_name varchar(20) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE payment_method ADD CONSTRAINT payment_pk PRIMARY KEY(id);
ALTER TABLE payment_method ADD CONSTRAINT payment_bk UNIQUE(payment_code);

CREATE TABLE price_list_event(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	price_code varchar(5) NOT NULL,
	price_name varchar(50) NOT NULL,
	price_nominal bigint NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE price_list_event ADD CONSTRAINT price_event_pk PRIMARY KEY(id);
ALTER TABLE price_list_event ADD CONSTRAINT price_event_bk UNIQUE(price_code);

CREATE TABLE events(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	event_code varchar(5) NOT NULL,
	event_title varchar(100) NOT NULL,
	event_provider varchar(100) NOT NULL,
	event_price bigint NOT NULL,
	event_time_start time,
	event_time_end time,
	event_date_start date NOT NULL,
	event_date_end date NOT NULL,
	is_approve boolean NOT NULL,
	category_id varchar(36) NOT NULL,
	type_id varchar(36) NOT NULL,
	price_id varchar(36) NOT NULL,
	attachment_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE events ADD CONSTRAINT event_pk PRIMARY KEY(id);
ALTER TABLE events ADD CONSTRAINT event_bk UNIQUE(event_code);
ALTER TABLE events ADD CONSTRAINT event_category_fk FOREIGN KEY(category_id) REFERENCES categories(id);
ALTER TABLE events ADD CONSTRAINT event_type_fk FOREIGN KEY(type_id) REFERENCES event_types(id);
ALTER TABLE events ADD CONSTRAINT event_price_fk FOREIGN KEY(price_id) REFERENCES price_list_event(id);
ALTER TABLE events ADD CONSTRAINT event_attachment_fk FOREIGN KEY(attachment_id) REFERENCES attachments(id);

CREATE TABLE enroll_events(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	enroll_code varchar(5) NOT NULL,
	enroll_invoice varchar(20),
	is_approve boolean NOT NULL,
	profile_id varchar(36) NOT NULL,
	attachment_id varchar(36),
	payment_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE enroll_events ADD CONSTRAINT enroll_pk PRIMARY KEY(id);
ALTER TABLE enroll_events ADD CONSTRAINT enroll_ck UNIQUE(profile_id, payment_id);
ALTER TABLE enroll_events ADD CONSTRAINT enroll_profile_fk FOREIGN KEY(profile_id) REFERENCES profiles(id);
ALTER TABLE enroll_events ADD CONSTRAINT enroll_attachment_fk FOREIGN KEY(attachment_id) REFERENCES attachments(id);
ALTER TABLE enroll_events ADD CONSTRAINT enroll_payment_fk FOREIGN KEY(payment_id) REFERENCES payment_method(id);

CREATE TABLE enroll_detail(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	event_id varchar(36),
	enroll_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE enroll_detail ADD CONSTRAINT detail_pk PRIMARY KEY(id);
ALTER TABLE enroll_detail ADD CONSTRAINT detail_ck UNIQUE(event_id, enroll_id);
ALTER TABLE enroll_detail ADD CONSTRAINT detail_event_fk FOREIGN KEY(event_id) REFERENCES events(id);
ALTER TABLE enroll_detail ADD CONSTRAINT detail_enroll_fk FOREIGN KEY(enroll_id) REFERENCES enroll_events(id);

CREATE TABLE price_list_member(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	price_code varchar(5) NOT NULL,
	price_nominal bigint NOT NULL,
	duration int NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE price_list_member ADD CONSTRAINT price_member_pk PRIMARY KEY(id);
ALTER TABLE price_list_member ADD CONSTRAINT price_member_bk UNIQUE(price_code);

CREATE TABLE subscriptions(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	subscription_code varchar(5) NOT NULL,
	subscription_duration date NOT NULL,
	is_approve boolean NOT NULL,
	profile_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE subscriptions ADD CONSTRAINT subs_pk PRIMARY KEY(id);
ALTER TABLE subscriptions ADD CONSTRAINT subs_bk UNIQUE(subscription_code);
ALTER TABLE subscriptions ADD CONSTRAINT subs_profile_fk FOREIGN KEY(profile_id) REFERENCES  profiles(id);

CREATE TABLE subscription_detail(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	price_id varchar(36) NOT NULL,
	subscription_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE subscription_detail ADD CONSTRAINT detail_subs_pk PRIMARY KEY(id);
ALTER TABLE subscription_detail ADD CONSTRAINT detail_subs_ck UNIQUE(price_id, subscription_id);
ALTER TABLE subscription_detail ADD CONSTRAINT detail_subs_fk FOREIGN KEY(subscription_id) REFERENCES subscriptions(id);
ALTER TABLE subscription_detail ADD CONSTRAINT detail_price_fk FOREIGN KEY(price_id) REFERENCES price_list_member(id);

CREATE TABLE social_media(
	id varchar(36) DEFAULT uuid_generate_v4(),
	social_media_code varchar(5) NOT NULL,
	social_media_name varchar(50) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE social_media ADD CONSTRAINT social_media_pk PRIMARY KEY(id);
ALTER TABLE social_media ADD CONSTRAINT social_media_bk UNIQUE(social_media_code);

CREATE TABLE profile_sosmed(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	profile_id varchar(36) NOT NULL,
	social_media_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);

ALTER TABLE profile_sosmed ADD CONSTRAINT profile_sosmed_pk PRIMARY KEY(id);
ALTER TABLE profile_sosmed ADD CONSTRAINT profile_sosmed_ck UNIQUE(profile_id, social_media_id);
ALTER TABLE profile_sosmed ADD CONSTRAINT profile_fk FOREIGN KEY(profile_id) REFERENCES profiles(id);
ALTER TABLE profile_sosmed ADD CONSTRAINT social_media_fk FOREIGN KEY(social_media_id) REFERENCES social_media(id);