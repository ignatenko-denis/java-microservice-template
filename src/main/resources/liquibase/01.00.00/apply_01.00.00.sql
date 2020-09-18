SET search_path = sample;

CREATE TABLE IF NOT EXISTS client
(
    id         BIGSERIAL PRIMARY KEY       NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    birthday   DATE                        NOT NULL,
    created_at TIMESTAMP(3) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    xml        XML                         NULL,
    status     VARCHAR(10)                 NULL,
    vip        BOOLEAN                     NOT NULL DEFAULT false,
    CONSTRAINT status_in_set CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED'))
);

COMMENT ON TABLE client IS 'Client table';
COMMENT ON COLUMN client.name IS 'Client Name';
