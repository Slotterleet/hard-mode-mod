Vars.content.units().each(u => {
  u.health *= 2;
});

Vars.content.blocks().each(b => {
  if (b instanceof Turret){
    var stack = b.requirements;
    for (var i = 0; i < stack.length; i++){
      stack[i].amount *= 2;
    }
    return;
  };
  if (b instanceof Reconstructor){
    var consumes = b.consumes.get(ConsumeType.item).items;
    for(var i = 0; i < consumes.length; i++){ 
      consumes[i].amount *= 3;
    };
    return;
  };
  if (b instanceof UnitFactory){
    var plans = b.plans;
    for(var i = 0; i < plans.length; i++){
      var stack = plans[i].requirements;
      for(var j; j < stack.length; j++){
        stack[j].amount *= 3;
      }
    };
    return;
  };
  if (b instanceof PowerGenerator){
    b.powerProduction = (b.powerProduction * 2/3);
    return;
  }
})