# Materialized View
Spring Boot and PostgreSQL materialized view demo

On PostgreSQL 
 - create a function that is called by the stored procedure and it in turn calls the materialized view
    `CREATE OR REPLACE FUNCTION refresh_mat_view()
    RETURNS TRIGGER LANGUAGE plpgsql
    AS $$
    BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY purchase_order_summary;
    RETURN NULL;
    END $$;`
- create a stored procedure (trigger)
    `CREATE TRIGGER refresh_mat_view_after_po_insert
        AFTER INSERT 
        ON purchase_order
        FOR EACH STATEMENT
        EXECUTE PROCEDURE refresh_mat_view();`
  

    
 - create the materialized view 
     `CREATE MATERIALIZED VIEW purchase_order_summary
    AS
    select 
        u.state,
        sum(p.price) as total_sale
    from 
        users u,
        product p,
        purchase_order po
    where 
        u.id = po.user_id
        and p.id = po.product_id
    group by u.state
    order by u.state
    WITH NO DATA;
    CREATE UNIQUE INDEX state_category ON purchase_order_summary (state);`
