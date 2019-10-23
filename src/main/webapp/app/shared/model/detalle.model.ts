export interface IDetalle {
  id?: number;
  numRecamara?: number;
  numCocina?: number;
  numBano?: number;
  numSala?: number;
  numEstudio?: number;
  numGarage?: number;
  numJardin?: number;
}

export class Detalle implements IDetalle {
  constructor(
    public id?: number,
    public numRecamara?: number,
    public numCocina?: number,
    public numBano?: number,
    public numSala?: number,
    public numEstudio?: number,
    public numGarage?: number,
    public numJardin?: number
  ) {}
}
