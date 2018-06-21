INSERT INTO categorydefinition (fieldname, name, elementenum1name, elementenum2name, elementfieldname, elementname)
values ("trees", "forest", "bowType", "powerType", "arrows", "elf");

INSERT INTO category (name, fieldvalue, categorydefinition_id)
values (SELECT name, trees, (SELECT id from categorydefinition where name = 'forest') FROM forest);

INSERT INTO element (name, enum1value, enum2value, fieldvalue, category_id)
values (select name, bowType, powerType, arrows, (
  SELECT id
  from category
  where name =
) from elf)