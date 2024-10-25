-- Define ENUM types
CREATE TYPE dual_state AS ENUM ('ATTRACTION', 'UTILITY', 'DUAL');
CREATE TYPE approval_status AS ENUM ('APPROVED', 'REJECTED');
CREATE TYPE day_of_week AS ENUM ('MO', 'TU', 'WE', 'TH', 'FR', 'SA', 'SU');
CREATE TYPE report_type AS ENUM ('SITE', 'REVIEW');
CREATE TYPE reaction_type AS ENUM ('LIKE', 'DISLIKE');
CREATE TYPE target AS ENUM ('USER', 'SITE');
CREATE TYPE user_class AS ENUM ('USER', 'ADMIN');