  /* PARAMETER: ${paramName}: ${dataType} */
  bundle := UFS.BUNDLE(
      UFS.CHANNEL_LIST(),
      UFS.SUBSYSTEM_LIST(),
      UFS.PARAMETER_LIST( -- список добавляемых параметров
          UFS.PARAMETER('${paramName}', -- имя параметра
                        '${isList}', -- признак списка, 0 = данный параметр не может хранить список значений
                        '${dataType}', -- тип значения параметра
                        '${description}', -- описание параметра
                        '${role}', -- имя роли параметра
                        '${paramType}', -- тип параметра
                        UFS.PARAM_VALUE_LIST( -- список значений параметра
                            UFS.PARAMETER_VALUE('${paramValue}', -- непосредственно значениепараметра
                                                '${channel}', -- канал, для которого определено значение
                                                '${subsystem}', -- подсистема, для которой определено значение
                                                NULL, -- ТБ не указан
                                                NULL, -- ОСБ не указан
                                                NULL, -- ВСП не указан
                                                'false') -- признак зоны
                        )
          )
      ));

  IF (UFS.CHECK_INSERT_PARAMETER(bundle.PARAMETERS) = 0) THEN
    UFS.INSERT_BUNDLE(bundle); -- вызов процедуры на добавление параметров, каналов и подсистем в ФП СУП
    COMMIT;
  END IF;


