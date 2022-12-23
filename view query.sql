-- book_datail_view sql문
select  a.bi_seq ,a.bi_title ,a.bi_price ,concat(a.bi_discount *100,'%') as bi_discount,a.bi_delivery 
	,a.bi_reg_dt ,a.bi_pi_seq ,a.bi_ti_seq ,a.bi_sales 
	, b.bti_intro, c.bii_image, c.bii_uri , d.bti_name, e.ai_name, round(f.score,2) as score
	,TRUNCATE(a.bi_price*(1-a.bi_discount),-3) as discount_price
	,TRUNCATE(a.bi_price*(1-a.bi_discount)*0.05,-1) as 'point' , f.review_count, g.ci_path , g.ci_uri , j.pi_name  
	,rank() over(ORDER BY a.bi_sales desc) as ranking
from book_info a 
left outer join book_text_intro b on b.bti_bi_seq = a.bi_seq 
left outer join book_intro_image c on c.bii_bi_seq =a.bi_seq 
left outer join book_translator_info d on a.bi_ti_seq = d.bti_seq 
join (
select h.bwi_bi_seq, group_concat(ai_name) as ai_name  from book_writer_info h join author_info i on h.bwi_ai_seq = i.ai_seq
group by h.bwi_bi_seq
) e on e.bwi_bi_seq = a.bi_seq 
left outer join (
select avg(ri_score)*2 as score, count(*) as review_count, ri_bi_seq  from review_info 
group by ri_bi_seq
) f on f.ri_bi_seq = a.bi_seq
join cover_image g on g.ci_bi_seq = a.bi_seq 
join publisher_info j on j.pi_seq = a.bi_pi_seq ;

-- review_detail_view sql문(review_info + 회원 아이디)
select a.*, REGEXP_REPLACE(b.mi_id, '(?<=.{2}).', '*') as mi_id
from review_info a
join member_info b on a.ri_mi_seq =b.mi_seq
order by ri_bi_seq ;

