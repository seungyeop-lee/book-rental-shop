package out

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
)

//go:generate mockgen -package=mockOut -source=./member_persistence.go -destination=./mock/member_persistence_mock.go --build_flags=--mod=mod

type MemberFinder interface {
	FindById(id vo.MemberId) (*domain.Member, error)
}

type MemberSaver interface {
	Save(member *domain.Member) (vo.MemberId, error)
}

type MemberUpdater interface {
	Update(id vo.MemberId, member *domain.Member) error
}

type MemberDeleter interface {
	Delete(id vo.MemberId) error
}
