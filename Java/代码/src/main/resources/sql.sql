SELECT `t_doa_batch`.`id`                   AS `batch_id`,
       `t_doa_batch`.`batch_no`             AS `batch_no`,
       `t_doa_batch`.`flight_origin`        AS `flight_origin`,
       `t_doa_batch`.`receiver_code`        AS `receiver_code`,
       `t_doa_batch`.`receiver_name`        AS `receiver_name`,
       `t_doa_batch`.`remark`               AS `remark`,

       CASE
           WHEN `t_doa_batch`.`num_tally` IS NOT NULL THEN
               `t_doa_batch`.`num_tally`
           ELSE 0
           END                              AS `num_tally`,

       CASE

           WHEN `t_doa_batch`.`num_doc` IS NOT NULL THEN
               `t_doa_batch`.`num_doc`
           ELSE 0
           END                              AS `num_doc`,

       CASE

           WHEN `t_doa_batch`.`wgt_tally` IS NOT NULL THEN
               `t_doa_batch`.`wgt_tally`
           ELSE 0
           END
                                            AS `wgt_tally`,

       CASE

           WHEN `t_doa_batch`.`wgt_doc` IS NOT NULL THEN
               `t_doa_batch`.`wgt_doc`
           ELSE 0
           END
                                            AS `wgt_doc`,
       `t_doa_waybill`.`id`                 AS `waybill_id`,
       `t_doa_waybill`.`wb_prefix`          AS `wb_prefix`,
       `t_doa_waybill`.`wb_no`              AS `wb_no`,
       `t_doa_waybill`.`cargo_name`         AS `cargo_name`,
       `t_doa_waybill`.`special_code`       AS `special_code`,
       `t_doa_waybill`.`num_total`          AS `num_total`,
       `t_doa_waybill`.`wgt_total`          AS `wgt_total`,
       `t_doa_waybill`.`pack`               AS `pack`,
       `t_pub_flight_dynamic`.`carrier`     AS `carrier`,
       `t_pub_flight_dynamic`.`id`          AS `flight_id`,
       `t_pub_flight_dynamic`.`flight`      AS `flight`,
       `t_pub_flight_dynamic`.`flight_date` AS `flight_date`,

       CASE

           WHEN
                   `t_doa_batch`.`num_tally` >= `t_doa_batch`.`num_doc`
                   AND `t_doa_batch`.`wgt_tally` >= `t_doa_batch`.`wgt_doc` THEN
               '0'
           ELSE '1'
           END
                                            AS `tally_completed`
FROM (( `t_doa_batch` JOIN `t_doa_waybill` )
         JOIN `t_pub_flight_dynamic` )
WHERE ((
           `t_doa_batch`.`wb_id` = `t_doa_waybill`.`id`
           )
    AND (`t_doa_batch`.`flight_id` = `t_pub_flight_dynamic`.`id`))