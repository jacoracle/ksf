import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDetalle, Detalle } from 'app/shared/model/detalle.model';
import { DetalleService } from './detalle.service';

@Component({
  selector: 'jhi-detalle-update',
  templateUrl: './detalle-update.component.html'
})
export class DetalleUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    numRecamara: [],
    numCocina: [],
    numBano: [],
    numSala: [],
    numEstudio: [],
    numGarage: [],
    numJardin: []
  });

  constructor(protected detalleService: DetalleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ detalle }) => {
      this.updateForm(detalle);
    });
  }

  updateForm(detalle: IDetalle) {
    this.editForm.patchValue({
      id: detalle.id,
      numRecamara: detalle.numRecamara,
      numCocina: detalle.numCocina,
      numBano: detalle.numBano,
      numSala: detalle.numSala,
      numEstudio: detalle.numEstudio,
      numGarage: detalle.numGarage,
      numJardin: detalle.numJardin
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const detalle = this.createFromForm();
    if (detalle.id !== undefined) {
      this.subscribeToSaveResponse(this.detalleService.update(detalle));
    } else {
      this.subscribeToSaveResponse(this.detalleService.create(detalle));
    }
  }

  private createFromForm(): IDetalle {
    return {
      ...new Detalle(),
      id: this.editForm.get(['id']).value,
      numRecamara: this.editForm.get(['numRecamara']).value,
      numCocina: this.editForm.get(['numCocina']).value,
      numBano: this.editForm.get(['numBano']).value,
      numSala: this.editForm.get(['numSala']).value,
      numEstudio: this.editForm.get(['numEstudio']).value,
      numGarage: this.editForm.get(['numGarage']).value,
      numJardin: this.editForm.get(['numJardin']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetalle>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
