package persistence

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/errors"
	"gorm.io/gorm"
)

func convertErrorWithValue[T any](v T, e error) (T, error) {
	if e == gorm.ErrRecordNotFound {
		return v, errors.NotFound
	}
	return v, e
}

func convertError(e error) error {
	if e == gorm.ErrRecordNotFound {
		return errors.NotFound
	}
	return e
}
