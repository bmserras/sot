binder.forField(widgets)
                        .withConverter(new Converter<Set<Widget>, List<SynopticWidget>>() {
                            @Override
                            public Result<List<SynopticWidget>> convertToModel(Set<Widget> widgets, ValueContext valueContext) {
                                List<SynopticWidget> synopticWidgets = new ArrayList<>();
                                List<Widget> widgetsList = new ArrayList<>(widgets);
                                widgetsList.forEach(w -> {
                                    SynopticWidget synopticWidget = new SynopticWidget();
                                    synopticWidget.setWidget(w);
                                    synopticWidgets.add(synopticWidget);
                                });
                                return Result.ok(synopticWidgets);
                            }

                            @Override
                            public Set<Widget> convertToPresentation(List<SynopticWidget> list, ValueContext valueContext) {
                                Set<Widget> widgetSet = new HashSet<>();
                                list.forEach(sw -> {
                                    widgetSet.add(sw.getWidget());
                                });
                                return widgetSet;
                            }
                        })
                                .bind(Synoptic::getWidgets, Synoptic::setWidgets);



        //RadarWidgetComponent radarWidget = new RadarWidgetComponent(1);

        /*NumberField numberField = new NumberField();
        numberField.setValue(1d);
        numberField.setStepButtonsVisible(true);
        numberField.setMin(0);
        numberField.setMax(2);
        numberField.setStep(0.1);

        numberField.addValueChangeListener(e -> {
            Double value = e.getValue();
            if (value == 1.0)
                radarWidget.setScale(0.99999);
            else radarWidget.setScale(value);
        });

        add(numberField, radarWidget);*/

        /*AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setWidth("50%");
        absoluteLayout.setHeightFull();
        add(absoluteLayout);

        absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("50%");
        horizontalLayout.setHeightFull();
        add(horizontalLayout);

        Button leftButton = new Button("<", e -> {
            radarWidget.setPosX(radarWidget.getPosX() - 1);
            absoluteLayout.removeAll();
            absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());
        });
        Button upButton = new Button("^");
        Button downButton = new Button("v");
        Button rightButton = new Button(">", e -> {
            radarWidget.setPosX(radarWidget.getPosX() + 1);
            absoluteLayout.removeAll();
            absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());
        });
        horizontalLayout.add(leftButton, upButton, downButton, rightButton);*/