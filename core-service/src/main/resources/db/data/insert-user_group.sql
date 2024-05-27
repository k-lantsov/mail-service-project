insert into public.user_group(user_id, group_id)
values ((select id from public.user where email = 'lantsov.kostya@gmail.com'), (select id from public.group where name = 'dev'));

insert into public.user_group(user_id, group_id)
values ((select id from public.user where email = 'k.lantsov@yahoo.com'), (select id from public.group where name = 'dev'));